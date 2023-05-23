package org.ts;

public class UnknownSizeException extends RuntimeException{
	public UnknownSizeException() {
	}

	public UnknownSizeException(String message) {
		super(message);
	}

	public UnknownSizeException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnknownSizeException(Throwable cause) {
		super(cause);
	}

	public UnknownSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
