package creditdirect.clientmicrocervice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Commune")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Commune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;


    @Column(name = "code_postal", unique = true)
    private String codePostal;
    @Enumerated(EnumType.STRING)


    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
    @JoinColumn(name = "wilaya_id") // Assuming column name for the relationship
    private Wilaya wilaya;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany
    private Set<Agence> agences = new HashSet<>();

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
