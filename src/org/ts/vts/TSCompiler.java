package org.ts.vts;

import org.ts.Space;
import org.ts.__unsafe__;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class TSCompiler {

	public static void main(String[] args) {
		//String source = "unsigned_int a = u16;";
		TSCompiler compiler = new TSCompiler(new File("test.vts"));
		System.out.println(Arrays.toString(compiler.tokenize()));
		Space space = new Space();
		space.vts(readAllLines(new File("test.vts")));
		System.out.println(space);
	}

	private Token[] tokenize() {
		return new Tokenizer(source).tokenize();
	}


	private final String source;

	private final Token[] tokens;




	private static String readAllLines(File file) {
		try {
			StringBuilder builder = new StringBuilder();
			List<String> lines = Files.readAllLines(file.toPath());
			for (String line : lines) {
				builder.append(line).append("\n");
			}
			return builder.toString();
		} catch (IOException e) {
			__unsafe__.getUnsafe().throwException(e);
			return null;
		}
	}

	public TSCompiler(File file) {
		this(readAllLines(file));
	}
	public TSCompiler(String source) {
		this.source = source;
		tokens = tokenize();
	}

	public byte[] compile(Token[] tokens) {
		Stack<Byte> bytes = new Stack<>();
		for (Token token : tokens) {
			if (token.type == TokenType.DIRECT) {
				if (token.text.equals("#head")) {
					//todo
				}
			}
		}
		throw new UnsupportedOperationException();
	}











	public byte[] compile() {
		return compile(tokens);
	}


}
