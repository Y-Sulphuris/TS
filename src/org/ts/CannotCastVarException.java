package org.ts;

public class CannotCastVarException extends RuntimeException{
	public CannotCastVarException() {
	}

	public CannotCastVarException(String message) {
		super(message);
	}

	public CannotCastVarException(String message, Throwable cause) {
		super(message, cause);
	}

	public CannotCastVarException(Throwable cause) {
		super(cause);
	}

	public CannotCastVarException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
