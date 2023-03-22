package org.ts;

public class NoFieldDefFoundException extends RuntimeException{
	public NoFieldDefFoundException() {
	}

	public NoFieldDefFoundException(String message) {
		super(message);
	}

	public NoFieldDefFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoFieldDefFoundException(Throwable cause) {
		super(cause);
	}

	public NoFieldDefFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
