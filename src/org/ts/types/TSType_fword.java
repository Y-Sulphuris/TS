package org.ts.types;

import org.ts.CannotCastVarException;
import org.ts.TSType;
import org.ts.vars.TS_fword;
import org.ts.vars.TS_octet;

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
		final int var_value;
		try {
			if (value instanceof Number) {
				var_value = ((Number) value).intValue();
			} else if (value instanceof String && ((String) value).startsWith("0x")) {
				String str = ((String) value).substring(2);
				if (str.length()!=6)  throw new Exception();
				var_value = (byte) Integer.parseInt(str,16);
			} else throw new Exception();
		} catch (Exception e) {
			throw new CannotCastVarException(value + " ("+value.getClass()+") to " + this.getTypeName());
		}
		return new TS_fword(var_value);
	}

	@Override
	public TS_fword defaultValue() {
		return new TS_fword(0);
	}

	@Override
	public boolean isPrimitive() {
		return true;
	}
}
