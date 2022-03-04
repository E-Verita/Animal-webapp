package Animal.webapp.models;

import Animal.webapp.models.enums.HosingType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    @NotNull

    private String username;
    @NotNull
    private String email;
    private String password;
    private String name;
    private Integer age;
    private String phone;
    private Boolean hasSmallChildren;
    private HosingType hosingType;
    private Boolean hasOtherAnimals;
    private Boolean hasActiveLifestyle;
    private Boolean canAdoptSickAnimal;
    }
