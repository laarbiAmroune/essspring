package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.config.JwtUtil;
import creditdirect.clientmicrocervice.entities.Compte;
import creditdirect.clientmicrocervice.repositories.CompteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.AuthenticationException;

// ...



@Service
public class CompteServiceImpl implements CompteService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CompteRepository compteRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Compte signUp(Compte compte) {
        try {
            if (isNinAlreadyExists(compte.getNin())) {
                throw new DuplicateKeyException("NIN already exists");
            }

            // Hash the password
            String hashedPassword = passwordEncoder.encode(compte.getPassword());
            compte.setPassword(hashedPassword);

            return compteRepository.save(compte);
        } catch (Exception e) {
            // You might handle the exception more gracefully here,
            // like custom exceptions or returning null based on your requirements.
            return null;
        }
    }

    @Override
    public boolean isNinAlreadyExists(String nin) {
        return compteRepository.existsByNin(nin);
    }
    @Override
    public String signInByNin(String nin, String password) {
        try {
            Compte compte = compteRepository.findByNin(nin);
            if (compte != null && passwordEncoder.matches(password, compte.getPassword())) {
                return jwtUtil.generateToken(compte.getId(), compte.getRole());
            } else {
                // Handle authentication failure
                return null;
            }
        } catch (EntityNotFoundException e) {
            // Handle the case where the compte with the given NIN is not found
            return null;
        } catch (Exception e) {
            // Handle other exceptions
            throw new RuntimeException("Internal server error", e);
        }
    }

    @Override
    public Compte findByNin(String nin) {
        return compteRepository.findByNin(nin);
    }


    @Override
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

}
