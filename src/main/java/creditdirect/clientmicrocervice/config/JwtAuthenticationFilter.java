package creditdirect.clientmicrocervice.config;

import creditdirect.clientmicrocervice.entities.RoleType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

// ... other imports
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String token = null;
        Long userId = null;
        RoleType userRole = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            try {
                userId = jwtUtil.extractUserId(token); // Implement a method to extract userId from the token
                Collection<SimpleGrantedAuthority> authorities = jwtUtil.extractRole(token); // Implement a method to extract user role
                //userRole = RoleType.valueOf(roleString);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, null, Collections.singleton(new SimpleGrantedAuthority(userRole.toString())));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {

            }
        }

        filterChain.doFilter(request, response);
    }
}
