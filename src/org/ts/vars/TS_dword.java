package org.ts.vars;

import org.ts.OverriddenTypes;
import org.ts.TSType;
import org.ts.TSVar;

public final class TS_dword extends TSVar {
	private final int value;

	public TS_dword(int value) {
		this.value = value;
	}

	@Override
	public int size() {
		return 4;
	}

	@Override
	public Object getValue() {
		return value;
	}


	@SuppressWarnings("unchecked")
	public static final TSType<TS_dword> type = OverriddenTypes.get("dword");

	@Override
	public TSType<TS_dword> getType() {
		return type;
	}
}
