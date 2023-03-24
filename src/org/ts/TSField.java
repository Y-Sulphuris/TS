package org.ts;

import java.nio.ByteBuffer;

public final class TSField extends TSComponent{

	private final String name;
	private final TSType type;

	public String getName() {
		return name;
	}
	public TSType getType() {
		return type;
	}

	TSField(TSType type, String name) {
		this.type = type;
		this.name = name;
	}



	@Override
	public int size() {
		return type.size() + name.length() + 1;
	}
}
