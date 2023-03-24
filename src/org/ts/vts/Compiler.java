package org.ts.vts;


public interface Compiler {
	byte[] compile();

	byte[] compile(Token[] tokens);
	Token[] tokenize();
}
