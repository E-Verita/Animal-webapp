package Animal.webapp.models;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp sent;
    private Timestamp answered;
    private Timestamp processed;
    @OneToOne
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteerId;
    @OneToOne
    @JoinColumn(name = "category_id")
    private Category categoryId;
    private String textToShelter;
    private String textToVolunteer;

}
