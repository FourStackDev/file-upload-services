package org.fourstack.fileupload.exceptionhandling;

/**
 * Class <b><i>DatabaseConstraintViolationException</i></b> is an alternative
 * Exception class for " org.hibernate.exception.ConstraintViolationException".
 * 
 * <p>
 * To handle the application related custom error messages this exception has
 * been introduced
 * </p>
 * 
 * @author Manjunath_HM
 *
 */
public class DatabaseConstraintViolationException extends RuntimeException {

	private String customErrorMessage;

	/**
	 * 
	 */
	private static final long serialVersionUID = 879060657021242L;

	/**
	 * 
	 */
	public DatabaseConstraintViolationException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DatabaseConstraintViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public DatabaseConstraintViolationException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DatabaseConstraintViolationException(Throwable cause) {
		super(cause);
	}

	public DatabaseConstraintViolationException(String message, Throwable cause, String customErrorMessage) {
		super(message, cause);
		this.customErrorMessage = customErrorMessage;
	}

	public String getCustomErrorMessage() {
		return customErrorMessage;
	}

	public void setCustomErrorMessage(String customErrorMessage) {
		this.customErrorMessage = customErrorMessage;
	}
}
