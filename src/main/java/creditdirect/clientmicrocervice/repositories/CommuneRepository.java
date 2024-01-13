package creditdirect.clientmicrocervice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import creditdirect.clientmicrocervice.entities.Commune;

@Repository
public interface CommuneRepository extends JpaRepository<Commune, Long> {
    Commune findByCodePostal(String postalCode);
    // You can add custom query methods specific to Commune entity if needed
}
