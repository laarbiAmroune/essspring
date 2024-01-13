package creditdirect.clientmicrocervice.repositories;

import creditdirect.clientmicrocervice.entities.TypeCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeCreditRepository extends JpaRepository<TypeCredit, Long> {
    List<TypeCredit> findByTypeFinancementId(Long typeFinancementId);
}
