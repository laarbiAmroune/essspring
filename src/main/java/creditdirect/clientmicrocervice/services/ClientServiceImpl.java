package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.entities.Client;
import creditdirect.clientmicrocervice.entities.Commune;
import creditdirect.clientmicrocervice.entities.Particulier;
import creditdirect.clientmicrocervice.repositories.ClientRepository;
import com.nimbusds.jose.*;

import java.util.*;

import creditdirect.clientmicrocervice.repositories.CommuneRepository;
import creditdirect.clientmicrocervice.repositories.ParticulierRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class ClientServiceImpl implements ClientService {

    private static final String SECRET_KEY = "ThisIsASecureSecretKeyWithAtLeast256BitsLength123456789012345678901234567890";
    // Replace with your actual secret key
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days in milliseconds
    private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailService emailService;
    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, BCryptPasswordEncoder passwordEncoder,EmailService emailService) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }


    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Long id, Client client) {
        if (clientRepository.existsById(id)) {
            client.setId(id);
            return clientRepository.save(client);
        }
        return null; // Or handle as per requirement
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }






    @Override
    public String login(String email, String password) {
        Client client = clientRepository.findByEmail(email);
        if (client != null && passwordEncoder.matches(password, client.getPassword())) {


         return generateToken(client);
        } else {
            return "Authentication failed";
        }
    }
    @Override
    public Map<String, Object> loginWithClientInfo(String email, String password) {
        try {
            Client client = clientRepository.findByEmail(email);
            Map<String, Object> response = new HashMap<>();

            if (client != null && passwordEncoder.matches(password, client.getPassword())) {
                if (client.isActivated()) {
                    String token = generateToken(client);
                    String clientType = getClientType(client);

                    response.put("client", client); // Adding client information to the response
                    response.put("role", clientType); // Adding client type to the response
                    response.put("token", token);
                } else {
                    response.put("error", "Account is not activated");
                }
            } else {
                response.put("error", "Invalid credentials");
            }

            return response;
        } catch (Exception e) {
            // Handle other exceptions if necessary
            throw new RuntimeException("Internal server error", e);
        }
    }
    private String getClientType(Client client) {
        if (client instanceof Particulier) {
            return "Particulier";
        } else {
            return "Client";
        }
    }

    private String generateToken(Client client) {
        try {

            String clientType = getClientType(client);
            System.out.println("Logged in as " + clientType);
            JWTClaimsSet claims = new JWTClaimsSet.Builder()

                    .subject(client.getEmail())
                    .claim("clientType", clientType.toString())
                    .claim("id", client.getId().toString()) // Include ID in the claim
                    .issueTime(new Date())
                    .expirationTime(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .build();

            JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.HS256)
                    .type(JOSEObjectType.JWT)
                    .build();

            SignedJWT signedJWT = new SignedJWT(header, claims);

            MACSigner signer = new MACSigner(SECRET_KEY);
            signedJWT.sign(signer);

            return signedJWT.serialize();
        } catch (JOSEException e) {
            logger.error("Error generating JWT token for email: {}", client, e);
            // Add additional handling here if necessary
            return null;
        }
    }
    @Autowired
    private ParticulierRepository particulierRepository;
   /* @Override
    public Particulier subscribeParticulier(Particulier particulier) {
        // Add any business logic or validation here before saving
        return particulierRepository.save(particulier);
    }
*/


    /*@Override
    public Particulier subscribeParticulier(Particulier particulier) {

        String generatedPassword = generateRandomPassword();
        System.out.println("generated passeword: " + generatedPassword);
        String hashedPassword = passwordEncoder.encode(generatedPassword);
        particulier.setPassword(hashedPassword );
        Particulier subscribedParticulier = particulierRepository.save(particulier);
        emailService.sendConfirmationEmail(subscribedParticulier.getEmail());

        // Retrieve Commune based on postal code
        String postalCode = particulier.getCodePostal(); // Assuming you have a method to get postal code from Particulier
        Commune commune = communeRepository.findByCodePostal(postalCode);

        if (commune != null) {
            subscribedParticulier.setCommune(commune); // Associate Particulier with Commune
            return particulierRepository.save(subscribedParticulier); // Save and return the updated Particulier
        } else {
            // Handle scenario when Commune is not found for the provided postal code
            return null;
        }

        return subscribedParticulier;
    }*/


    @Override
    public Particulier subscribeParticulier(Particulier particulier) {
        String generatedPassword = generateRandomPassword();
        System.out.println("generated password: " + generatedPassword);

        String hashedPassword = passwordEncoder.encode(generatedPassword);
        particulier.setPassword(hashedPassword);

        // Save the Particulier first
        Particulier subscribedParticulier = particulierRepository.save(particulier);

        emailService.sendConfirmationEmail(subscribedParticulier.getEmail(),generatedPassword);

        // Retrieve Commune based on postal code
        String postalCode = particulier.getCodePostal(); // Assuming you have a method to get postal code from Particulier
        Commune commune = communeRepository.findByCodePostal(postalCode);
           System.out.println("postalCode postalCode: " + postalCode);

        if (commune != null) {
            subscribedParticulier.setCommune(commune); // Associate Particulier with Commune
            particulierRepository.save(subscribedParticulier);
            return subscribedParticulier; // Save and return the updated Particulier
        } else {
            // Handle scenario when Commune is not found for the provided postal code
            return null;
        }
    }








    /////////////// generate password ////////////////////////////
    private String generateRandomPassword() {

        String uuid = UUID.randomUUID().toString().replace("-", "");


        return uuid.substring(0, 8);
    }

    @Autowired
    private CommuneRepository communeRepository;
    @Override
    public Client updateClientPassword(Long clientId, String password) {
        Optional<Client> optionalClient = clientRepository.findById(clientId);

        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            String hashedPassword = passwordEncoder.encode(password);
            client.setPassword(hashedPassword);
            return clientRepository.save(client);
        } else {
            // Handle case where client with provided ID doesn't exist
            // You can throw an exception or return null/throw a custom exception
            return null;
        }
    }


    @Override
    public void activateClientByEmail(String email) {
        Client client = clientRepository.findByEmail(email);
        if (client == null) {
            throw new EntityNotFoundException("Client not found with email: " + email);
        }

        client.setActivated(true);
        clientRepository.save(client);
    }


    //////////////mot de passe oublier+envoi un mailavec un nouveux mot de passe //////////////

    @Override
    public void resetPasswordByEmail(String email) {
        Client client = clientRepository.findByEmail(email);

        if (client != null) {
            // Générer un nouveau mot de passe
            String newPassword = generateRandomPassword();
            String hashedNewPassword = passwordEncoder.encode(newPassword);
            client.setPassword(hashedNewPassword);

            // Sauvegarder le client avec le nouveau mot de passe
            clientRepository.save(client);

            // Envoyer un e-mail avec le nouveau mot de passe
            emailService.sendPasswordResetEmail(client.getEmail(), newPassword, client.getEmail());
        } else {
            throw new EntityNotFoundException("Client non trouvé avec l'e-mail : " + email);
        }
    }




}
