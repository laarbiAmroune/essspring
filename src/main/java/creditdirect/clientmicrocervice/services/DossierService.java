package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.entities.Agence;
import creditdirect.clientmicrocervice.entities.Dossier;
import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DossierService {

    Dossier affectiondossieragence(Long dossierId);
    Dossier updateFilesForDossier(Long dossierId, MultipartFile[] files);

    //////////////
    ///////////////////////////////////////////////////////////////////////
    Long getSingleAgenceIdByParticulierId(Long particulierId);

    List<Agence> findAgencesByCommuneId(Long communeId);

    @Transactional
    Long findAgenceRegionaleIdByParticulierId(Long idParticulier);

    List<Dossier> getAllDossiers();
    Dossier getDossierById(Long id);
    List<Dossier> getDossiersByClientId(Long clientId);

  // Long addDossier(Long clientId, Long typeCreditId, Long typeFinancementId, MultipartFile[] files, String simulationInfo);


    Dossier assignDossierToCourtier(Long dossierId, Long courtierId);
    List<Dossier> getDossiersForCourtier(Long courtierAgenceId);

    List<Dossier> getDossiersencoursForCourtier(Long courtierId);

    List<Dossier> getTraiteeDossiersByCourtier(Long courtierId);

    void updateDossierStatusToTraitee(Long dossierId);



    Dossier addDossier(Dossier dossier);

    void updateDossiersStatusToTraitee(List<Long> dossierIds);

    List<Dossier> getAcceptedAndRejectedDossiersByCourtier(Long courtierId);

    //void deleteFileByDossierIdAndFileName(Long dossierId, String fileName);
    boolean deleteFileByDossierIdAndFileName(Long dossierId, String fileName);


    List<Dossier> getAllDossiersByAgence(Long assignedAgence);


    void updateDossierStatusDirector_ACCEPTED(Long dossierId);

    void updateDossierStatusDirector_REFUSE(Long dossierId);
    byte[] downloadFileByDossierIdAndFileName(Long dossierId, String fileName) throws IOException;



    void updateStatusToRenvoyer(Long idDossier, Long idCompte, String comment);


    void setStatusToAccepter(Long idDossier, String comment, Long idCompte);
    void setStatusToRefuser(Long idDossier, String comment, Long idCompte);
}
