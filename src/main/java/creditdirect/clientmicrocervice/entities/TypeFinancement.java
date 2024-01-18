package creditdirect.clientmicrocervice.entities;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "typefinancement")
public class TypeFinancement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TypeFinancementID")
    private Long id;

    @Column(name = "nom_financement")
    private String nomFinancement;
}


