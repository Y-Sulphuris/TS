package org.ts.types;

import org.ts.vars.TS_dqword;

import java.math.BigInteger;

public final class TSType_dqword extends org.ts.TSType<TS_dqword> {
	public static final int TYPEWORD = 7;

	public TSType_dqword() {
		super("dqword",TYPEWORD);
	}

	@Override
	public int size() {
		throw new UnsupportedOperationException();
	}

	@Override
	public TS_dqword valueOf(Object value) {
		BigInteger var_value;
		throw new UnsupportedOperationException();
		//return null;
	}

	@Override
	public TS_dqword defaultValue() {
		return new TS_dqword(new BigInteger("0"));
	}

	@Override
	public boolean isPrimitive() {
		return true;
	}
}
