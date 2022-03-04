package Animal.webapp.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum Region {
    RIGA ("Riga"),
    KURZEME ("Kurzeme"),
    ZEMGALE ("Zemgale"),
    VIDZEME ("Vidzeme"),
    LATGALE ("Latgale");

    private final String name;
}
