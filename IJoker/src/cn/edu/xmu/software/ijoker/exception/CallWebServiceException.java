package cn.edu.xmu.software.ijoker.exception;


public class CallWebServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1103987753957513376L;

	public CallWebServiceException() {
	}

	public CallWebServiceException(String detailMessage) {
		super(detailMessage);
	}

	public CallWebServiceException(Throwable throwable) {
		super(throwable);
	}

	public CallWebServiceException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

}
