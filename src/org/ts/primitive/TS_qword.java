package org.ts.primitive;

import org.ts.TSType;

public class TS_qword extends TSType {
	public static final int TYPEWORD = 5;

	public TS_qword() {
		super("qword",TYPEWORD);
	}

	@Override
	public int size() {
		return 8;
	}
}
