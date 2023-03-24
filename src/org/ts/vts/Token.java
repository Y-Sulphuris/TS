package org.ts.vts;

public class Token {
	final TokenType type;
	final String text;

	public Token(TokenType type, String text) {
		this.type = type;
		this.text = text;
	}
	public Token(TokenType type, char ch) {
		this(type,String.valueOf(ch));
	}

	@Override
	public String toString() {
		return "Token{type=" + type + ", text='" + text + "'}";
	}
}


enum TokenType {
	KEYWORD,
	NUMBER,
	TEXT,
	QSTART,//[
	QEND,//]
	OPERATOR, // = & *
	OPEN,//{
	CLOSE,//}
	OPENROUND,//(
	CLOSEROUND,//)
	END,//;
	EOF,//end of file

	ERROR
}