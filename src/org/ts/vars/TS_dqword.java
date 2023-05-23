package org.ts.vars;

import org.ts.OverrideTypes;
import org.ts.TSType;
import org.ts.TSVar;

import java.math.BigInteger;

public final class TS_dqword extends TSVar {

	private final BigInteger value;

	public TS_dqword(BigInteger value) {
		this.value = value;
	}

	@Override
	public int size() {
		return 16;
	}

	@Override
	public Object getValue() {
		return value;
	}


	@SuppressWarnings("unchecked")
	public static final TSType<TS_dqword> type = OverrideTypes.get("dqword");

	@Override
	public TSType<TS_dqword> getType() {
		return type;
	}
}