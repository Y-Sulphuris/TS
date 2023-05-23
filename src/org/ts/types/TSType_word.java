package org.ts.types;


import org.ts.CannotCastVarException;
import org.ts.vars.TS_word;

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
		try {
			if (value instanceof Number) {
				var_value = ((Number) value).shortValue();
			} else if (value instanceof String && ((String) value).startsWith("0x")) {
				String str = ((String) value).substring(2);
				if (str.length() != 4)  throw new Exception();
				var_value = (short) Integer.parseInt(str,16);
			} else throw new Exception();
		} catch (Exception e) {
			throw new CannotCastVarException(value + " ("+value.getClass()+") to " + this.getTypeName());
		}
		/*if (value instanceof String) {
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
		} else throw new IllegalArgumentException("cannot convert " + value + "to " + getTypeName());*/
		return new TS_word(var_value);
	}

	@Override
	public TS_word defaultValue() {
		return new TS_word((short)0);
	}

	@Override
	public boolean isPrimitive() {
		return true;
	}
}
