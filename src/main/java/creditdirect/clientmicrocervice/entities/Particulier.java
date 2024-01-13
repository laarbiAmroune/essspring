package creditdirect.clientmicrocervice.entities;

import lombok.Data;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@PrimaryKeyJoinColumn(name = "particulier_id")
public class Particulier extends Client {

    @Column(name = "civilite")
    private String civilite;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "nationalite")
    private String nationalite;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "ville")
    private String ville;

    @Column(name = "code_postal")
    private String codePostal;

    @Column(name = "resides_in_algeria")
    private boolean residesInAlgeria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commune_id") // Name of the foreign key column in Particulier table
    private Commune commune; // Represents the Commune associated with this Particulier

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;


    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
