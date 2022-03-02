package Animal.webapp.models;

import Animal.webapp.models.enums.AdoptionStauts;
import Animal.webapp.models.enums.HosingType;
import Animal.webapp.models.enums.Type;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Type type;
    private String name;
    private String ageGroup;                                //ToDo - enum, array, let it stay? Options 3-6months, 6-12months,  1-4year, 4-8year, senior
    private String info;
    private Date registrationDate;
    private Boolean canLiveWithSmallChildren;
    private HosingType hosingType;
    private Boolean canLiveWithOtherAnimals;
    private Boolean needsActiveLifestyle;
    private Boolean isSick;
    private AdoptionStauts adoptionStatus;
    private String pictureUrl;
}
