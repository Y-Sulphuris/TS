package org.ts.primitive;

import org.ts.TSVar;
import org.ts.vars.TS_octet;

import java.math.BigInteger;

public final class TSType_octet extends org.ts.TSType {
	public static final int TYPEWORD = 1;
	public TSType_octet() {
		super("octet", TYPEWORD);
	}

	@Override
	public int size() {
		throw new UnsupportedOperationException();
	}

	@Override
	public TSVar valueOf(Object value) {
		byte var_value;
		if (value instanceof String) {
			String vts = (String) value;
			if (vts.endsWith(";")) vts = vts.substring(0, vts.length() - 1);
			if (vts.endsWith("b")) {
				vts = vts.substring(0, vts.length() - 1);
				var_value = Byte.parseByte(vts);
			} else {
				var_value = new BigInteger(vts).byteValue();//no prefix = no security checks
			}
		} else if (value instanceof Number) {
			var_value = ((Number) value).byteValue();
		} else throw new IllegalArgumentException("cannot convert " + value + "to " + getTypeName());
		return new TS_octet(var_value);
	}
}
