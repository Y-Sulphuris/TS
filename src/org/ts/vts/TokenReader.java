package org.ts.vts;

import org.ts.*;

import java.util.ArrayList;
import java.util.List;

public class TokenReader {
	private final Token[] tokens;
	private int counter = 0;
	public TokenReader(String vts) {
		tokens = new Tokenizer(vts).tokenize();
	}

	public void invoke(Space space, boolean runtime) {
		Token token = nextToken();
		while (token.type != TokenType.EOF) {
			switch (token.type) {
				case KEYWORD:
					if (token.text.equals("type")) {
						final String typename;
						final int typeword;
						final TSField[] fields;
						token = nextToken();
						if (token.type == TokenType.TEXT) {
							typename = token.text;
							typeword = space.nextFreeTypeword();
							token = nextToken();
						} else if (token.type == TokenType.QSTART) {
							typeword = (int) nextToken().getNumber();
							token = nextToken();
							if (token.type != TokenType.QEND) {
								throw new UnexpectedTokenException(token);
							}
							token = nextToken();
							if (token.type == TokenType.TEXT) {
								typename = token.text;
								token = nextToken();
							} else
								throw new UnexpectedTokenException(token);
						} else
							throw new UnexpectedTokenException(token);

						if (token.type == TokenType.OPERATOR) {
							if (!token.text.equals("="))
								throw new UnexpectedTokenException(token);
							token = nextToken();
							String fieldTypename;
							if (token.type == TokenType.TEXT)
								fieldTypename = token.text;
							else
								throw new UnexpectedTokenException(token);
							TSType<?> fieldType = space.getType(fieldTypename);

							fields = new TSField[]{new TSField(fieldType,"")};
						} else if (token.type == TokenType.OPEN) {
							token = nextToken();
							List<TSField> fieldList = new ArrayList<>();
							while (token.type != TokenType.CLOSE) {
								final TSType<?> fieldType;
								final String fieldName;
								if (token.type == TokenType.TEXT) {
									fieldType = space.getType(token.text);
									token = nextToken();
								} else throw new UnexpectedTokenException(token);
								if (token.type == TokenType.TEXT) {
									fieldName = token.text;
								} else throw new UnexpectedTokenException(token);
								if (nextToken().type != TokenType.END) throw new UnexpectedTokenException(token);
								fieldList.add(new TSField(fieldType,fieldName));
								token = nextToken();
							}
							if (nextToken().type != TokenType.END) throw new UnexpectedTokenException(token);
							fields = fieldList.toArray(new TSField[0]);
						} else throw new UnexpectedTokenException(token);


						space.defineType(typename,typeword,fields);
					}
					break;
				case TEXT:
				{
					TSType type = space.getType(token.text);
					String variableName;
					Object[] values;
					token = nextToken();
					if (token.type == TokenType.TEXT) {
						variableName = token.text;
						if (!space.fieldExist(variableName)) {
							token = nextToken();
							if (token.type == TokenType.OPERATOR && token.text.equals("=")) {
								token = nextToken();
								if (token.type == TokenType.OPEN) {
									throw new UnsupportedOperationException();
								} else {
									if (!type.isOneFieldType()) throw new UnexpectedTokenException(token);
									space.defineVar(variableName,type.valueOf(token.text));
									token = nextToken();
									if (token.type != TokenType.END) throw new UnexpectedTokenException(token);
								}

							}
						} else throw new IllegalArgumentException("name " + variableName + "was already taken");
					} else throw new UnexpectedTokenException(token);
				}
				break;
				case END:
					// TODO: 25.03.2023
			}







			token = nextToken();
		}


	}

	private Token nextToken() {
		return tokens[counter++];
	}
}
