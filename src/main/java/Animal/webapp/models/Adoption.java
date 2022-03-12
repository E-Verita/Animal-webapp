package Animal.webapp.models;

import Animal.webapp.models.enums.Status;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table
public class Adoption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animalId;
    @OneToOne
    @JoinColumn(name = "adopter_id")
    private Adopter adopterId;
    @OneToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelterId;
    @CreationTimestamp
    private Timestamp sent;
    @UpdateTimestamp
    private Timestamp processed;
    private Status status;
    private String adoptersText;
    private String sheltersText;

}
