package org.ts.primitive;

import org.ts.TSType;
import org.ts.TSVar;
import org.ts.vars.TS_dword;

public final class TSType_dword extends TSType<TS_dword> {
	public static final int TYPEWORD = 3;

	public TSType_dword() {
		super("dword",TYPEWORD);
	}

	@Override
	public int size() {
		throw new UnsupportedOperationException();
	}

	@Override
	public TS_dword valueOf(Object value) {
		return null;
	}
}
