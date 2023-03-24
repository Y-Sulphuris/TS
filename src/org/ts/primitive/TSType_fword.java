package org.ts.primitive;

import org.ts.TSType;
import org.ts.vars.TS_fword;

public final class TSType_fword extends TSType<TS_fword> {
	public static final int TYPEWORD = 4;
	public TSType_fword() {
		super("fword",TYPEWORD);
	}

	@Override
	public int size() {
		throw new UnsupportedOperationException();
	}

	@Override
	public TS_fword valueOf(Object value) {
		return null;
	}
}
