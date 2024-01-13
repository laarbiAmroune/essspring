package creditdirect.clientmicrocervice.repositories;

import creditdirect.clientmicrocervice.entities.Particulier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticulierRepository extends JpaRepository<Particulier, Long> {
    // Additional custom queries can be added here if needed
}
