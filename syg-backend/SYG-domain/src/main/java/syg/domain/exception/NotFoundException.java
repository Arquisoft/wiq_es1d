package syg.domain.exception;

public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8737231427849268021L;

	/**
	 * Constructor con parámetros
	 * 
	 * @param message
	 */
	public NotFoundException(String message) {
		super(message);
	}
}
