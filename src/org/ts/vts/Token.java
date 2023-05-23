package org.ts.vts;

import org.ts.UnexpectedTokenException;

public class Token {
	public final TokenType type;
	public final String text;

	public Token(TokenType type, String text) {
		this.type = type;
		this.text = text;
	}
	public Token(TokenType type, char ch) {
		this(type,String.valueOf(ch));
	}

	@Override
	public String toString() {
		return "Token{" + type + (type != TokenType.EOF ? " = '" + text + "'}" : "}");
	}

	public long getNumber() {
		if (type == TokenType.NUMBER) return Long.valueOf(text);
		else throw new UnexpectedTokenException(this + "is not number");
	}

	public boolean isHexNumber() {
		return type == TokenType.NUMBER && text.startsWith("0x");
	}
}


