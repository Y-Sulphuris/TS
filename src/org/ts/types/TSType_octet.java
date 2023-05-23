package org.ts.types;

import org.ts.CannotCastVarException;
import org.ts.vars.TS_octet;



public final class TSType_octet extends org.ts.TSType<TS_octet> {
	public static final int TYPEWORD = 1;
	public TSType_octet() {
		super("octet", TYPEWORD);
	}

	@Override
	public int size() {
		throw new UnsupportedOperationException();
	}

	@Override
	public TS_octet valueOf(Object value) {
		final byte var_value;
		try {
			if (value instanceof Number) {
				var_value = ((Number) value).byteValue();
			} else if (value instanceof String && ((String) value).startsWith("0x")) {
				String str = ((String) value).substring(2);
				if (str.length()!=2 || str.startsWith("-")) throw new Exception();
				var_value = (byte) Integer.parseInt(str,16);
			} else throw new Exception();
		} catch (Exception e) {
			throw new CannotCastVarException(value + " ("+value.getClass()+") to " + this.getTypeName());
		}
		/*if (value instanceof String) {
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
		} else throw new IllegalArgumentException("cannot convert " + value + "to " + getTypeName());*/
		return new TS_octet(var_value);
	}

	@Override
	public TS_octet defaultValue() {
		return new TS_octet((byte)0);
	}

	@Override
	public boolean isPrimitive() {
		return true;
	}
}
