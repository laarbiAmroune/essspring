package creditdirect.clientmicrocervice.config;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import creditdirect.clientmicrocervice.entities.RoleType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}") // Load secret key from configuration
    private String secretKey;

    @Value("${jwt.expiration.ms}") // Load expiration time from configuration
    private long expirationTimeMs;

    public String generateToken(Long userId, RoleType role) {
        try {
            Instant now = Instant.now();
            Date issuedAt = Date.from(now);
            Date expiresAt = Date.from(now.plusMillis(expirationTimeMs));

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(Long.toString(userId))
                    .claim("role", role.toString())
                    .issueTime(issuedAt)
                    .expirationTime(expiresAt)
                    .build();

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

            MACSigner signer = new MACSigner(secretKey);
            signedJWT.sign(signer);

            return signedJWT.serialize();
        } catch (JOSEException e) {
            // Handle exception
            return null;
        }
    }

    public Long extractUserId(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
            return Long.parseLong(claimsSet.getSubject());
        } catch (Exception e) {
            // Gérer les exceptions en cas d'erreur lors de l'extraction de l'identifiant
            return null;
        }
    }

    public ArrayList<SimpleGrantedAuthority> extractRole(String token) {
        ArrayList<SimpleGrantedAuthority> roles = new ArrayList<>();
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
            roles.add(new SimpleGrantedAuthority((String)claimsSet.getStringClaim("role")));
            return roles;
        } catch (Exception e) {
            // Gérer les exceptions en cas d'erreur lors de l'extraction du rôle
            return roles;
        }
    }
}
