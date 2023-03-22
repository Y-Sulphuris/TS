package org.ts.primitive;


public final class TS_word extends org.ts.TSType {
	public static final int TYPEWORD = 2;
	public TS_word() {
		super("word", TYPEWORD);
	}

	@Override
	public int size() {
		return 2;
	}
}
