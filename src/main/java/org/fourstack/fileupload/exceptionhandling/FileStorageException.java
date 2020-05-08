package org.fourstack.fileupload.exceptionhandling;

/**
 * Class <b><i>FileStorageException</i></b> is sub class of RuntimeException.
 * This is used to handle the custom exceptions in the application
 * 
 * @author Manjunath_HM
 *
 */
public class FileStorageException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7596507126173334597L;

	/**
	 * 
	 */
	public FileStorageException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public FileStorageException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public FileStorageException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public FileStorageException(Throwable cause) {
		super(cause);
	}

}
