package enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import syg.domain.model.enums.CategoryEnum;

public class CategoryEnumTests {

    @Test
    @DisplayName("Verifica que las constantes del enumerado estan presented")
    public void testEnumConstants() {
        assertNotNull(CategoryEnum.valueOf("Sport"));
        assertNotNull(CategoryEnum.valueOf("Countries"));
        assertNotNull(CategoryEnum.valueOf("Science"));
        assertNotNull(CategoryEnum.valueOf("Cine"));
        assertNotNull(CategoryEnum.valueOf("Videogames"));
    }

    @Test
    @DisplayName("Verifica la relación entre el valor y la label")
    public void testEnumProperties() {
        assertEquals("Q349", CategoryEnum.Sport.getValue());
        assertEquals("Deportes", CategoryEnum.Sport.getLabel());

        assertEquals("Q6256", CategoryEnum.Countries.getValue());
        assertEquals("Paises", CategoryEnum.Countries.getLabel());

        assertEquals("Q336", CategoryEnum.Science.getValue());
        assertEquals("Ciencia", CategoryEnum.Science.getLabel());

        assertEquals("Q11424", CategoryEnum.Cine.getValue());
        assertEquals("Cine", CategoryEnum.Cine.getLabel());

        assertEquals("Q7889", CategoryEnum.Videogames.getValue());
        assertEquals("Videojuegos", CategoryEnum.Videogames.getLabel());
    }
}