package org.ts;

import org.ts.vts.Token;

public class UnexpectedTokenException extends RuntimeException{
	public UnexpectedTokenException(Token token, String msg, Throwable cause) {
		this(String.valueOf(token) + ": " + msg, cause);
	}
	public UnexpectedTokenException(Token token, String msg) {
		this(String.valueOf(token) + ": " + msg);
	}
	public UnexpectedTokenException(Token token) {
		this(String.valueOf(token));
	}

	public UnexpectedTokenException(String message) {
		super(message);
	}

	public UnexpectedTokenException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnexpectedTokenException(Throwable cause) {
		super(cause);
	}
}
