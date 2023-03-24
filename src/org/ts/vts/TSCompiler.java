package org.ts.vts;

import org.ts.GOTOException;

import java.util.Arrays;
import java.util.Stack;

class TSCompiler implements Compiler{

	public static void main(String[] args) {
		String source = "unsigned_int a = u16;";
		Compiler compiler = new TSCompiler(source);
		System.out.println(Arrays.toString(compiler.tokenize()));
	}




	private final String source;
	int counter = 0;

	private final Token[] tokens;


	public TSCompiler(String source) {
		this.source = source;
		tokens = tokenize();
	}

	@Override
	public byte[] compile(Token[] tokens) {
		Stack<Byte> bytes = new Stack<>();
		for (int i = 0; i < tokens.length; i++) {
			//todo
		}
		throw new UnsupportedOperationException();
	}





















	@Override
	public byte[] compile() {
		return compile(tokens);
	}


	@Override
	public Token[] tokenize() {
		Stack<Token> tokenStack = new Stack<>();
		Token token = nextToken();
		tokenStack.push(token);
		while (token.type != TokenType.EOF) {
			token = tokenStack.push(nextToken());
		}


		Token[] result = new Token[tokenStack.size()];
		counter = 0;
		return tokenStack.toArray(result);
	}
	private Token nextToken() {
		char ch = nextChar();
		//skip
		while (ch == ' ' || ch == '\n' || ch == '\t') {
			ch = nextChar();
		}
		//comments
		if (ch == '/') {
			ch = nextChar();
			if (ch == '/') {
				while (ch != '\n') {
					ch = nextChar();
				}
			} else if (ch == '*') {
				while (true) {
					ch = nextChar();
					if (ch == '*') {
						ch = nextChar();
						if (ch == '/') {
							break;
						}
					}
				}
			}
		}
		//skip after comments
		while (ch == ' ' || ch == '\n' || ch == '\t') {
			ch = nextChar();
		}
		try {
			if (containsChar(numbers, ch) || ch == 'u') {
				if (ch == 'u') {
					if (!containsChar(numbers, seeNextChar())) {
						throw new GOTOException();
					}
				}
				boolean hex = false;
				StringBuilder builder = new StringBuilder().append(ch);
				if (ch == '0') {
					if (seeNextChar() == 'x') {
						builder.append(nextChar());
						hex = true;
					}
				}
				ch = nextChar();
				while (hex ? containsChar(hexNumbers,ch) : containsChar(numbers,ch) || ch == '.') {
					builder.append(ch);
					ch = nextChar();
				}
				if (containsCharCase(numberPostfix,ch))
					builder.append(ch);
				else counter--;
				return new Token(TokenType.NUMBER,builder.toString());
			}
		} catch (GOTOException ignored) {}
		if (containsChar(validNameCharacters,ch)) {
			StringBuilder builder = new StringBuilder();
			do {
				builder.append(ch);
				ch = nextChar();
			} while (containsChar(validNameCharacters,ch));
			counter--;
			String res = builder.toString();
			return new Token(isKeyword(res) ? TokenType.KEYWORD : TokenType.TEXT, res);
		}
		if (containsChar(operators,ch) || ch == '.') {
			return new Token(TokenType.OPERATOR,ch);
		}
		TokenType type;
		switch (ch) {
			case ';':
				type = TokenType.END;
				break;
			case '[':
				type = TokenType.QSTART;
				break;
			case ']':
				type = TokenType.QEND;
				break;
			case '{':
				type = TokenType.OPEN;
				break;
			case '}':
				type = TokenType.CLOSE;
				break;
			case '(':
				type = TokenType.OPENROUND;
				break;
			case ')':
				type = TokenType.CLOSEROUND;
				break;
			case '\0':
				type = TokenType.EOF;
				break;
			default:
				return new Token(TokenType.ERROR,"Unexpected token '" + ch + "' at " + counter);
		}
		return new Token(type,ch);
	}

	private char nextChar() {
		try {
			return source.charAt(counter++);
		} catch (IndexOutOfBoundsException e) {
			return '\0';
		}
	}
	private char seeNextChar() {
		try {
			return source.charAt(counter);
		} catch (IndexOutOfBoundsException e) {
			return '\0';
		}
	}

	private boolean containsChar(String str, char ch) {
		return str.contains(String.valueOf(ch).toLowerCase());
	}
	private boolean containsCharCase(String str, char ch) {
		return str.contains(String.valueOf(ch));
	}
	private boolean isKeyword(String string) {
		for (String keyword : keywords) {
			if (string.equals(keyword)) return true;
		}
		return false;
	}



	private static final String numbers = "1234567890";
	private static final String hexNumbers = numbers+"ABCDEF";
	private static final String validNameCharacters = "abcdefghijklmnopqrstuvwxyz_"+numbers;
	private static final String numberPostfix = "bsilLfdDC";
	private static final String operators = "=*&";
	public static final String[] keywords = {
			"typedef",
			"static",
			"unsafe",
			"struct",

			"octet",
			"word",
			"dword",
			"fword",
			"qword",
			"tword",
			"dqword"
	};
}
