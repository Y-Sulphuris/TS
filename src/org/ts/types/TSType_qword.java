package org.ts.types;

import org.ts.TSType;
import org.ts.vars.TS_qword;

public final class TSType_qword extends TSType<TS_qword> {
	public static final int TYPEWORD = 5;

	public TSType_qword() {
		super("qword",TYPEWORD);
	}

	@Override
	public int size() {
		throw new UnsupportedOperationException();
	}

	@Override
	public TS_qword valueOf(Object value) {
		return null;
	}

	@Override
	public TS_qword defaultValue() {
		return new TS_qword(0);
	}

	@Override
	public boolean isPrimitive() {
		return true;
	}
}
