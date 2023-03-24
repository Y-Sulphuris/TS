package org.ts.primitive;

import org.ts.TSVar;

import java.math.BigInteger;

public final class TSType_dqword extends org.ts.TSType {
	public static final int TYPEWORD = 7;

	public TSType_dqword() {
		super("dqword",TYPEWORD);
	}

	@Override
	public int size() {
		throw new UnsupportedOperationException();
	}

	@Override
	public TSVar valueOf(Object value) {
		BigInteger var_value;
		return null;
	}
}
