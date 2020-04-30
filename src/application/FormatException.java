package application;
/**
 * General runtime exception for formatting errors
 * @author ateam85
 *
 */
public class FormatException extends RuntimeException {
	/**
	 * Constructs a new FormatException
	 * @param message - the message w/ the exception
	 */
	public FormatException(String message) {
		super(message);
	}
}
