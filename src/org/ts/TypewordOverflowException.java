package org.ts;

public class TypewordOverflowException extends RuntimeException{
	public TypewordOverflowException() {
	}

	public TypewordOverflowException(String message) {
		super(message);
	}

	public TypewordOverflowException(String message, Throwable cause) {
		super(message, cause);
	}

	public TypewordOverflowException(Throwable cause) {
		super(cause);
	}
}
