package org.ts;

import java.util.HashMap;

public class TSType extends TSComponent {
	private final int typeword;
	private final String name;

	private final HashMap<String, TSType> fields = new HashMap<>();

	TSType(Space space, String name, int typeword) {
		super(space);
		if (typeword > TSUtils.TYPEWORD_MAX)
			throw new IllegalArgumentException("3 bytes overflow");
		if (name.length() > 0xFF)
			throw new IllegalArgumentException("Byte overflow");
		this.typeword = typeword;
		this.name = name;
	}

	public int getTypeword() {
		return typeword;
	}

	public String getName() {
		return name;
	}

	void addField(String name, TSType type) {
		fields.put(name, type);
	}

	public TSType getFieldType(String name) {
		return fields.get(name);
	}
}
