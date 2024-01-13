package creditdirect.clientmicrocervice.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Wilaya")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Wilaya {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wilaya_name")
    private String wilayaName;





}
