package cn.edu.xmu.software.ijoker.exception;


public class UploadException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1103987753957513376L;

	public UploadException() {
	}

	public UploadException(String detailMessage) {
		super(detailMessage);
	}

	public UploadException(Throwable throwable) {
		super(throwable);
	}

	public UploadException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

}
