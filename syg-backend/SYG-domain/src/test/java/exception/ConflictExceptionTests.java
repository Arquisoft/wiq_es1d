package exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import configuration.UnitDomainTest;
import syg.domain.exception.ConflictException;

@UnitDomainTest
public class ConflictExceptionTests {

    @Test
    public void testConflictExceptionMessage() {
        String expectedMessage = "This is a conflict error";
        ConflictException exception = assertThrows(ConflictException.class, () -> {
            throw new ConflictException(expectedMessage);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testConflictExceptionCatch() {
        boolean exceptionCaught = false;
        try {
            throw new ConflictException("Conflict exception");
        } catch (ConflictException e) {
            exceptionCaught = true;
            assertEquals("Conflict exception", e.getMessage());
        }
        assertTrue(exceptionCaught, "The exception should be caught");
    }
}