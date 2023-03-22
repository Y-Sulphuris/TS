package org.ts.primitive;

import org.ts.TSType;

public class TS_dword extends TSType {
	public static final int TYPEWORD = 3;

	public TS_dword() {
		super("dword",TYPEWORD);
	}

	@Override
	public int size() {
		return 4;
	}
}
