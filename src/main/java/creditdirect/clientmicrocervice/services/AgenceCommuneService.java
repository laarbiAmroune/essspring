package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.entities.Agence;
import creditdirect.clientmicrocervice.entities.Commune;
import java.util.List;
public interface AgenceCommuneService {


    Agence createAgence(Agence agence);



    void addCommuneToAgence(Long agenceId, Long communeId);









    List<Commune> getAllCommunes() throws Exception;
    Commune getCommuneById(Long id);
    Commune createCommune(Commune commune);
    Commune updateCommune(Long id, Commune updatedCommune);
    void deleteCommune(Long id);

    List<Agence> getAllAgences();


    Agence getAgenceById(Long assignedAgenceId);
}
