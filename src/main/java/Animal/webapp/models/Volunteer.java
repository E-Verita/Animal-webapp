package Animal.webapp.models;

import Animal.webapp.models.enums.HelpFrequency;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String name;
    private Date birthday;
    private Timestamp registrationDate;
    private ArrayList<Category> helpCategories;
    private HelpFrequency helpFrequency;
    private String pictureUrl;
    private Integer unAnsweredMessages;

}
