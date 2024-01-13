package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.entities.Compte;

import javax.naming.AuthenticationException;
import java.util.List;

public interface CompteService {
    Compte signUp(Compte compte);


    String signInByNin(String nin, String password) throws AuthenticationException;

    Compte findByNin(String nin);

    List<Compte> getAllComptes();

    boolean isNinAlreadyExists(String nin);
}
