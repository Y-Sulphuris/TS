package org.ts.vars;

import org.ts.OverrideTypes;
import org.ts.TSType;
import org.ts.TSVar;

public class TS_fword extends TSVar {
	@Override
	public int size() {
		return 0;
	}

	@Override
	public Object getValue() {
		return null;
	}

	@SuppressWarnings("unchecked")
	public static final TSType<TS_fword> type = OverrideTypes.get("fword");
	@Override
	public TSType<TS_fword> getType() {
		return type;
	}
}
