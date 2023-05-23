package org.ts.vars;

import org.ts.OverrideTypes;
import org.ts.TSType;
import org.ts.TSVar;

public final class TS_fword extends TSVar {

	private final int value;

	public TS_fword(int value) {
		this.value = value & 0xFFFFFF;
	}

	@Override
	public int size() {
		return 6;
	}

	@Override
	public Object getValue() {
		return value & 0xFFFFFF;
	}

	@SuppressWarnings("unchecked")
	public static final TSType<TS_fword> type = OverrideTypes.get("fword");
	@Override
	public TSType<TS_fword> getType() {
		return type;
	}
}
