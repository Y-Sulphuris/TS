package org.ts.vars;

import org.ts.OverriddenTypes;
import org.ts.TSType;
import org.ts.TSVar;

import java.math.BigInteger;

public final class TS_tword extends TSVar {

	private final BigInteger value;

	public TS_tword(BigInteger value) {
		this.value = value;
	}

	@Override
	public int size() {
		return 10;
	}

	@Override
	public Object getValue() {
		return value;
	}


	@SuppressWarnings("unchecked")
	public static final TSType<TS_tword> type = OverriddenTypes.get("tword");

	@Override
	public TSType<TS_tword> getType() {
		return type;
	}
}
