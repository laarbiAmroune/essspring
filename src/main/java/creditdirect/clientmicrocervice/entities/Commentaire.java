package creditdirect.clientmicrocervice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Commentaires")
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CommentaireID")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Dossier_id")
    private Dossier dossier;

    @ManyToOne // Many comments can be associated with one compte
    @JoinColumn(name = "compte_id")
    private Compte compte;

    @Column(name = "comment")
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private DossierStatus status;

    @Column(name = "comment_date", updatable = false)
    @CreationTimestamp
    private LocalDateTime commentDate;
}
