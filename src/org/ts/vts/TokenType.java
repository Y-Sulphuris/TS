package org.ts.vts;

public enum TokenType {
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
	DIRECT,

	ERROR
}
