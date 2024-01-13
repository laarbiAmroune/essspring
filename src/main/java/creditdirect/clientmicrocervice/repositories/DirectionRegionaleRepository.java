package creditdirect.clientmicrocervice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import creditdirect.clientmicrocervice.entities.DirectionRegionale;

@Repository
public interface DirectionRegionaleRepository extends JpaRepository<DirectionRegionale, Long> {
    // You can add custom queries here if needed
}
