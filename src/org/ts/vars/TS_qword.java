package org.ts.vars;

import org.ts.OverriddenTypes;
import org.ts.TSType;
import org.ts.TSVar;

public final class TS_qword extends TSVar {

	private final long value;

	public TS_qword(long value) {
		this.value = value;
	}


	@Override
	public int size() {
		return 8;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@SuppressWarnings("unchecked")
	public static final TSType<TS_qword> type = OverriddenTypes.get("qword");
	@Override
	public TSType<TS_qword> getType() {
		return type;
	}
}
