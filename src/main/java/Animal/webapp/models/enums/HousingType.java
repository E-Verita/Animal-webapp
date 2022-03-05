package Animal.webapp.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HousingType {
    APARTMENT ("apartment"),
    HOUSE ("house");

    private final String name;
}
