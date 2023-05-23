package org.ts;

import org.ts.vars.TS_Structure;

public final class TSField extends TSComponent{

	private final String name;
	private final TSType<?> type;

	public String getName() {
		return name;
	}
	public TSType<?> getType() {
		return type;
	}

	public TSField(TSType<?> type, String name) {
		this.type = type;
		this.name = name;
	}

	@Override
	public int size() {
		return type.size() + name.length() + 1;
	}

	public String getTypeName() {
		return getType().getTypeName();
	}

	public TSVar get(TSFieldContainer structure) {
		return structure.getValue(this);
	}
}
