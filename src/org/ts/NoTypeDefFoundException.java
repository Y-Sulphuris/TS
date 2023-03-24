package org.ts;

public class NoTypeDefFoundException extends RuntimeException{
	public NoTypeDefFoundException() {
	}

	public NoTypeDefFoundException(int typeword) {
		this(String.valueOf(typeword));
	}

	public NoTypeDefFoundException(String message) {
		super(message);
	}

	public NoTypeDefFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoTypeDefFoundException(Throwable cause) {
		super(cause);
	}

	public NoTypeDefFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
