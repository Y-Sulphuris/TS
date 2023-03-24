package org.ts.primitive;


import org.ts.vars.TS_word;

import java.math.BigInteger;

public final class TSType_word extends org.ts.TSType<TS_word> {
	public static final int TYPEWORD = 2;
	public TSType_word() {
		super("word", TYPEWORD);
	}

	@Override
	public int size() {
		throw new UnsupportedOperationException();
	}

	@Override
	public TS_word valueOf(Object value) {
		final short var_value;
		if (value instanceof String) {
			String vts = (String) value;
			if (vts.endsWith(";")) vts = vts.substring(0, vts.length() - 1);
			if (vts.endsWith("s")) {
				vts = vts.substring(0, vts.length() - 1);
				var_value = Short.parseShort(vts);
			} else {
				var_value = new BigInteger(vts).shortValue();
			}
		} else if (value instanceof Number) {
			var_value = ((Number) value).shortValue();
		} else throw new IllegalArgumentException("cannot convert " + value + "to " + getTypeName());
		return new TS_word(var_value);
	}
}
