package Animal.webapp.models;

import Animal.webapp.models.enums.Region;
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
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;
    private Region region;
    private String address;
    private String phone;
    private String registrationNumber;
    private String bankingInfo;
    @CreationTimestamp
    private Date registrationDate;

    public List getRegionList() {
        List<Region> regionList = new ArrayList<Region>(EnumSet.allOf(Region.class));
        return regionList;
    }
}
