package creditdirect.clientmicrocervice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@Table(name = "Dossier")
public class Dossier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DossierID")
    private Long id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Column(name = "nom_dossier")
    private String nomDossier;

    @ManyToOne
    @JoinColumn(name = "client_id") // Name of the foreign key column in DemandeCredit table
    private Client client; // Represents a single Client for each DemandeCredit

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
    @JoinColumn(name= "type_credit")
    private TypeCredit typeCredit; // Change the type from TypeCredit to String


    @ManyToOne
    @JoinColumn(name = "directeur_agence_id")
    private Compte directeurAgence;


    @ElementCollection
    @CollectionTable(name = "CreditAttachments", joinColumns = @JoinColumn(name = "Dossier_id"))
    private List<AttachedFile> attachedFiles;



    @ManyToOne
    @JoinColumn(name = "courtier_id")
    private Compte assignedCourtier;


    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private DossierStatus status = DossierStatus.NON_TRAITEE;

    @ManyToOne
    @JoinColumn(name = "agence_id")
    private Agence assignedagence;

    @ManyToOne
    @JoinColumn(name = "direction_regionale_id")
    private DirectionRegionale assigneddirectionregionnale;





    @Column(name = "montant_habitation")
    private Double montantHabitation; // Montant de l'habitation

    @Column(name = "revenue_emprunteur")
    private Double revenueEmprunteur; // Revenue de l'emprunteur

    @Column(name = "age_emprunteur")
    private Integer ageEmprunteur; // L'âge de l'emprunteur

    @Column(name = "credit_souhaite")
    private Double creditSouhaite; // Le crédit souhaité

    @Column(name = "duree_financement")
    private Integer dureeFinancement; // La durée de financement



    @Column(name = "revenue_co_emprunteur")
    private Double revenueCoEmprunteur; // Revenue de co-emprunteur

    @Column(name = "age_co_emprunteur")
    private Integer ageCoEmprunteur; // L'âge de co-emprunteur



    @Column(name = "montant_revenue_immobilier")
    private Double montantRevenueImmobilier; // Mentionnez ce montant revenue immobilier



    @Column(name = "montant_autre_revenue")
    private Double montantAutreRevenue; // Mentionnez ce montant autre revenue



    @Column(name = "montant_autre_financement_en_cours")
    private Double montantAutreFinancementEnCours; // Mentionnez ce montant autre financement en cours

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;





    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    @OneToMany(mappedBy = "dossier", cascade = CascadeType.ALL)
    private List<Commentaire> commentaires = new ArrayList<>();



    @OneToMany(mappedBy = "dossier", cascade = CascadeType.ALL)
    private List<DossierAction> actions = new ArrayList<>();



    public void setStatus(DossierStatus status) {
        this.status = status;
        DossierAction action = new DossierAction();
        action.setStatus(status);
        action.setActionDate(LocalDateTime.now());
        action.setDossier(this); // Set the dossier in the action
        this.actions.add(action);
    }

   public List<DossierAction> getActions() {
        return Collections.unmodifiableList(actions);
    }

}

