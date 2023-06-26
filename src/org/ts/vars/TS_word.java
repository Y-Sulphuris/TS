package org.ts.vars;

import org.ts.OverriddenTypes;
import org.ts.TSType;
import org.ts.TSVar;

public final class TS_word extends TSVar {

	private final short value;

	public TS_word(short value) {
		this.value = value;
	}


	@Override
	public int size() {
		return 2;
	}

	@Override
	public Object getValue() {
		return value;
	}




	@SuppressWarnings("unchecked")
	public static final TSType<TS_word> type = OverriddenTypes.get("word");

	@Override
	public TSType<TS_word> getType() {
		return type;
	}
}
