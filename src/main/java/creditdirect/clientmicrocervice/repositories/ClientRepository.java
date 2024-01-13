package creditdirect.clientmicrocervice.repositories;

import creditdirect.clientmicrocervice.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
    // Additional custom queries can be added here if needed
}
