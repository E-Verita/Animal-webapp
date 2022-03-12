package Animal.webapp.models;

import Animal.webapp.models.enums.AdoptionStatus;
import Animal.webapp.models.enums.AgeGroup;
import Animal.webapp.models.enums.HousingType;
import Animal.webapp.models.enums.Type;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
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
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Type type;
    private String name;
    private AgeGroup ageGroup;
    private String info;
    @CreationTimestamp
    private Date registrationDate;
    private Boolean canLiveWithSmallChildren;
    private HousingType housingType;
    private Boolean canLiveWithOtherAnimals;
    private Boolean needsActiveLifestyle;
    private Boolean isSick;
    private AdoptionStatus adoptionStatus;
    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;
    private String pictureUrl;

    public List getTypeList() {
        List<Type> typeList = new ArrayList<Type>(EnumSet.allOf(Type.class));
        return typeList;
    }

    public List getAgeGroupList() {
        List<AgeGroup> ageGroupList = new ArrayList<AgeGroup>(EnumSet.allOf(AgeGroup.class));
        return ageGroupList;
    }

    public List getHousingTypeList() {
        List<HousingType> housingTypeList = new ArrayList<HousingType>(EnumSet.allOf(HousingType.class));
        return housingTypeList;
    }
    public List getAdoptionStatusList() {
        List<AdoptionStatus> adoptionStatusList = new ArrayList<AdoptionStatus>(EnumSet.allOf(AdoptionStatus.class));
        return adoptionStatusList;
    }

}
