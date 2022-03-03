package Animal.webapp.models.enums;

public enum Region {
    RIGA ("Riga"),
    KURZEME ("Kurzeme"),
    ZEMGALE ("Zemgale"),
    VIDZEME ("Vidzeme"),
    LATGALE ("Latgale");

    private final String region;

    Region(String region) {
        this.region = region;
    }
}
