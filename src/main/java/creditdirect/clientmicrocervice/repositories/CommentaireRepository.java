package creditdirect.clientmicrocervice.repositories;

import creditdirect.clientmicrocervice.entities.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
    // You can add custom query methods if needed
}

