package org.ts.primitive;

import org.ts.TSType;
import org.ts.vars.TS_tword;

import java.math.BigInteger;

public final class TSType_tword extends TSType<TS_tword> {
	public static final int TYPEWORD = 6;

	public TSType_tword() {
		super("tword",TYPEWORD);
	}

	@Override
	public int size() {
		throw new UnsupportedOperationException();
	}

	@Override
	public TS_tword valueOf(Object value) {
		final BigInteger var_value;
		if (value instanceof String) {
			String vts = (String) value;
			if (vts.endsWith(";")) vts = vts.substring(0, vts.length() - 1);
			var_value = new BigInteger(vts);
		} else if (value instanceof Number) {
			var_value = BigInteger.valueOf(((Number) value).longValue());
		} else throw new IllegalArgumentException("cannot convert " + value + "to " + getTypeName());
		return new TS_tword(var_value);
	}
}
