package creditdirect.clientmicrocervice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;
@Entity
@Data
@Table(name = "DossierActions")
public class DossierAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DossierStatus status;

    private LocalDateTime actionDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "dossier_id")
    private Dossier dossier;
}
