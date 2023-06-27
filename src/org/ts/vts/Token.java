package org.ts.vts;

import org.ts.UnexpectedTokenException;

public class Token {
	public final TokenType type;
	public final String text;
	private final int line;

	public Token(TokenType type, String text, int line) {
		this.type = type;
		this.text = text;
		this.line = line;
	}
	public Token(TokenType type, char ch, int line) {
		this(type,String.valueOf(ch),line);
	}

	@Override
	public String toString() {
		return "Token{" + type + (type != TokenType.EOF ? " = '" + text + "' at line " + line +"}" : "}");
	}

	public long getNumber() {
		if (type == TokenType.NUMBER) return Long.parseLong(text);
		else throw new UnexpectedTokenException(this + "is not number");
	}

	public boolean isHexNumber() {
		return type == TokenType.NUMBER && text.startsWith("0x");
	}
}


