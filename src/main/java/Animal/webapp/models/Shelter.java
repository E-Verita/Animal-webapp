package Animal.webapp.models;

import Animal.webapp.models.enums.Region;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

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

    @NotNull
    @Size (min=3, max=45, message = "Please enter the name of the shelter")
    private String name;

    @Size (min=6, max=45, message = "Please enter valid email")
    @NotNull
    private String email;

    @Size (min=5, max=20, message = "Password must be 5-25 characters long ")
    @NotNull
    private String password;

    @NotNull
    private Region region;
    @NotNull
    private String address;
    @NotNull
    private String phone;
    @NotNull
    private String registrationNumber;
    @NotNull
    private String bankingInfo;
    @CreationTimestamp
    private Date registrationDate;

}
