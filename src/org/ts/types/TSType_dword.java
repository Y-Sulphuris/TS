package org.ts.types;

import org.ts.TSType;
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
		if (value instanceof Number) return new TS_dword(((Number) value).intValue());
		if (value instanceof String) return new TS_dword(Integer.parseInt((String)value));
		else throw new IllegalArgumentException(String.valueOf(value));
	}

	@Override
	public TS_dword defaultValue() {
		return new TS_dword(0);
	}

	@Override
	public boolean isPrimitive() {
		return true;
	}

}
