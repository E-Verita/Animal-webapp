package Animal.webapp.models;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserLogin {
    private String email;
    private String password;
}
