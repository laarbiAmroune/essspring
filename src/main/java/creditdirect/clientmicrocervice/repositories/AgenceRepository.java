package creditdirect.clientmicrocervice.repositories;

import creditdirect.clientmicrocervice.entities.Agence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenceRepository extends JpaRepository<Agence, Long> {
    // Custom query methods can be added here if needed
}
