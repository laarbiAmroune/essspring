package creditdirect.clientmicrocervice.repositories;

import creditdirect.clientmicrocervice.entities.Wilaya;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WilayaRepository extends JpaRepository<Wilaya, Long> {
    // You can add custom query methods here if needed
}
