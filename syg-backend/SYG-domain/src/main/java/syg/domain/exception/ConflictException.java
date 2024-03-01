package syg.domain.exception;

public class ConflictException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8737231427849268021L;

	/**
	 * Constructor con parámetros
	 * 
	 * @param message
	 */
	public ConflictException(String message) {
		super(message);
	}
}