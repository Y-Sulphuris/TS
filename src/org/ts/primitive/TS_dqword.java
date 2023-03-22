package org.ts.primitive;

public class TS_dqword extends org.ts.TSType {
	public static final int TYPEWORD = 7;

	public TS_dqword() {
		super("dqword",TYPEWORD);
	}

	@Override
	public int size() {
		return 16;
	}
}
