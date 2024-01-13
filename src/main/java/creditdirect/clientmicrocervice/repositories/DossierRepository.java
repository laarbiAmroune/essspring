package creditdirect.clientmicrocervice.repositories;


import creditdirect.clientmicrocervice.entities.Agence;
import creditdirect.clientmicrocervice.entities.Dossier;
import creditdirect.clientmicrocervice.entities.DossierStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DossierRepository extends JpaRepository<Dossier, Long> {


    Optional<Dossier> getDossierById(Long dossierId);

    List<Dossier> findByClientId(Long clientId);


    //List<Dossier> findAllByAgenceIdAndStatus(Long courtierAgenceId, DossierStatus dossierStatus);

    List<Dossier> findAllByAssignedCourtier_IdAndStatus(Long courtierId, DossierStatus dossierStatus);

    List<Dossier> findAllByAssignedagenceIdAndStatus(Long courtierId, DossierStatus dossierStatus);


    List<Dossier> findAllByAssignedagence(Agence assignedAgence);

    List<Dossier> findByAssignedCourtierIdAndStatusIn(Long courtierId, List<DossierStatus> statuses);


}
