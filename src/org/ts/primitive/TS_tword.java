package org.ts.primitive;

import org.ts.TSType;

public class TS_tword extends TSType {
	public static final int TYPEWORD = 6;

	public TS_tword() {
		super("tword",TYPEWORD);
	}

	@Override
	public int size() {
		return 10;
	}
}
