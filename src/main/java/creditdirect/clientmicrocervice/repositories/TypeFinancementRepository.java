package creditdirect.clientmicrocervice.repositories;

import creditdirect.clientmicrocervice.entities.TypeFinancement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeFinancementRepository extends JpaRepository<TypeFinancement, Long> {
    // Additional custom queries can be added here if needed
}
