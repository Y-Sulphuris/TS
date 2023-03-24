package org.ts.vars;

import org.ts.OverrideTypes;
import org.ts.TSType;
import org.ts.TSVar;

public final class TS_octet extends TSVar {

	private final byte value;

	public TS_octet(byte value) {
		this.value = value;
	}

	@Override
	public int size() {
		return 1;
	}


	@Override
	public Object getValue() {
		return value;
	}


	@SuppressWarnings("unchecked")
	public static final TSType<TS_octet> type = OverrideTypes.get("octet");
	@Override
	public TSType<TS_octet> getType() {
		return type;
	}
}
