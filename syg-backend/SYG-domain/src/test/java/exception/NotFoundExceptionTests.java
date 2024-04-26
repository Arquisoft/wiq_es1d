package exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import configuration.UnitDomainTest;
import syg.domain.exception.NotFoundException;

@UnitDomainTest
public class NotFoundExceptionTests {

    @Test
    public void testNotFoundExceptionMessage() {
        String expectedMessage = "This is a not found error";
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            throw new NotFoundException(expectedMessage);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testNotFoundExceptionCatch() {
        boolean exceptionCaught = false;
        try {
            throw new NotFoundException("Conflict exception");
        } catch (NotFoundException e) {
            exceptionCaught = true;
            assertEquals("Conflict exception", e.getMessage());
        }
        assertTrue(exceptionCaught, "The exception should be caught");
    }
}
