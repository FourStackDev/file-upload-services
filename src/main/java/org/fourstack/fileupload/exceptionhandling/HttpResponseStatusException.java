package org.fourstack.fileupload.exceptionhandling;

/**
 * Class <b><i>HttpResponseStatusException</i></b> is a sub-class for
 * RuntimeException. This exception is used to handle the bad http requests
 * posted to the application.
 * 
 * @author Manjunath_HM
 *
 */
public class HttpResponseStatusException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8564744199720473540L;

	/**
	 * 
	 */
	public HttpResponseStatusException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public HttpResponseStatusException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public HttpResponseStatusException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public HttpResponseStatusException(Throwable cause) {
		super(cause);
	}

}
