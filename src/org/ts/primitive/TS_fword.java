package org.ts.primitive;

import org.ts.TSType;

public class TS_fword extends TSType {
	public static final int TYPEWORD = 4;
	public TS_fword() {
		super("fword",TYPEWORD);
	}

	@Override
	public int size() {
		return 6;
	}
}
