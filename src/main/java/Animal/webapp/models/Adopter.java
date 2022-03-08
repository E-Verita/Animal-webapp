package Animal.webapp.models;

import Animal.webapp.models.enums.HousingType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table
public class Adopter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String name;
    private Integer age;
    private String phone;
    private Boolean hasSmallChildren;
    private HousingType housingType;
    private Boolean hasOtherAnimals;
    private Boolean hasActiveLifestyle;
    private Boolean canAdoptSickAnimal;

    public List getHousingTypeList() {
        List<HousingType> housingTypeList = new ArrayList<HousingType>(EnumSet.allOf(HousingType.class));
        return housingTypeList;
    }
}


