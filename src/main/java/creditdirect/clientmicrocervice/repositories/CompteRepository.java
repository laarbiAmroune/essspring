package creditdirect.clientmicrocervice.repositories;

import creditdirect.clientmicrocervice.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {




    Compte findByNin(String nin);

    boolean existsByNin(String nin);
}
