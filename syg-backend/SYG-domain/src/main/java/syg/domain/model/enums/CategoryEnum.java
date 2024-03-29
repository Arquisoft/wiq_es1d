package syg.domain.model.enums;

public enum CategoryEnum {
    Sport("Q349", "Deportes"),
    Countries("Q6256", "Paises"),
    Science("Q336", "Ciencia"),
    Cine("Q11424", "Cine"),
    Videogames("Q7889", "Videojuegos");

    private final String value;
    private final String label;

    private CategoryEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

}
