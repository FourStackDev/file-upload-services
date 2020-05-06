package org.fourstack.fileupload.exceptionhandling;

public class ResourceFileNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6363591674190074423L;

	/**
	 * 
	 */
	public ResourceFileNotFoundException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ResourceFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ResourceFileNotFoundException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ResourceFileNotFoundException(Throwable cause) {
		super(cause);
	}

}
