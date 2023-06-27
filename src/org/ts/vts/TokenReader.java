package org.ts.vts;

import org.ts.*;
import org.ts.types.TSTypeArray;
import org.ts.types.TSTypePointer;
import org.ts.vars.TS_Array;
import org.ts.vars.TS_Structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
							if (!token.text.equals("=")) {
								throw new UnexpectedTokenException(token);
							}
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
								} else if (token.type == TokenType.KEYWORD){
									if (token.text.equals("struct")) {
										throw new UnsupportedOperationException();
									} else {
										fieldType = space.getType(token.text);
										token = nextToken();
									}
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
					token = nextToken();
					while (token.type != TokenType.TEXT) {
						if (token.type == TokenType.QSTART) {//[
							token = nextToken();//]
							if (token.type != TokenType.QEND) throw new UnexpectedTokenException(token,"']' expected");
							type = TSTypeArray.forParent(type);
							token = nextToken();//name or?
						} else if (token.text.equals("*")) {
							type = TSTypePointer.forParent(type);
							token = nextToken();//name or?
						}
					}

					if (token.type == TokenType.TEXT) {
						variableName = token.text;
						if (!space.fieldExist(variableName)) {
							token = nextToken();
							if (token.type == TokenType.OPERATOR && token.text.equals("=")) {
								space.defineVar(variableName, readFieldValue(type));
								token = nextToken();
								if (token.type != TokenType.END) throw new UnexpectedTokenException(token, "';' expected");
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

	private TSVar readFieldValue(TSType type) {
		Token token = nextToken();
		if (token.type == TokenType.OPEN) {
			if (type.isPrimitive()) throw new UnexpectedTokenException(token,"invalid primitive type declaration {}");
			if (type.isArray()) {
				//если это массив, объявленный через {};
				TSTypeArray type_a = ((TSTypeArray) type);
				int fieldIndex = 0;
				List<TSVar> elements = new ArrayList<>();

				while(token.type != TokenType.CLOSE) {//token - pre-next element
					elements.add(readFieldValue(type_a.getTargetType()));//token - element
					token = nextToken();//token = , or }
					if (token.type != TokenType.CLOSE && !Objects.equals(token.text, ",")) throw new UnexpectedTokenException(token,"',' or '}' expected");
				}

				return new TS_Array(type_a.getTargetType(),elements.toArray(new TSVar[0]));
			} else {
				//если это составной тип, объявленный через {};
				TSTypeCompound type_c = ((TSTypeCompound) type);

				TSVar[] values = new TSVar[type_c.getFields().length];



				token = nextToken();
				while (token.type != TokenType.CLOSE) {
					//token there = variable name
					if (token.type != TokenType.TEXT) throw new UnexpectedTokenException(token);
					String structVarName = token.text;
					int targetFieldIndex = type_c.getFieldIndex(structVarName);
					token = nextToken();
					if (token.type == TokenType.OPERATOR && token.text.equals("=")) {
						values[targetFieldIndex] = readFieldValue(type_c.getField(targetFieldIndex).getType());
					} else throw new UnexpectedTokenException(token);
					token = nextToken();
				}

				return new TS_Structure(type_c,values);
			}
		} else if (token.type == TokenType.OPENROUND) {
			int fieldIndex = 0;
			TSTypeCompound type_c = ((TSTypeCompound) type);
			TSVar[] values = new TSVar[type_c.getFields().length];

			for(; fieldIndex < values.length; fieldIndex++) {
				values[fieldIndex] = readFieldValue(type_c.getField(fieldIndex).getType());
				token = nextToken();
				if (fieldIndex == values.length - 1) {
					if (token.type != TokenType.CLOSEROUND)
						throw new UnexpectedTokenException(token,"')' expected");
				} else {
					if (token.type == TokenType.CLOSEROUND || !token.text.equals(","))
						throw new UnexpectedTokenException(token,"',' expected");
				}
			}

			return new TS_Structure(type_c,values);
		} else {
			//if this is one field type
			if (!type.isOneFieldType()) throw new UnexpectedTokenException(token,"type must have only one field to use this syntax");
			TSVar result;
			try {
				result = type.valueOf(token.text);
			} catch (Exception e) {
				throw new UnexpectedTokenException(token, "parse error", e);
			}
			return result;
		}
	}
	private Token nextToken() {
		return tokens[counter++];
	}
	private Token currentToken() {
		return tokens[counter];
	}
}
