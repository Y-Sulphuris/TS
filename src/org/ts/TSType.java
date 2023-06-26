package org.ts;

import org.ts.types.TSTypeArray;
import org.ts.types.TSTypePointer;

public abstract class TSType<T extends TSVar> extends TSComponent {

	private final int typeword;
	private final String name;


	public TSType(String name, int typeword) {
		super();
		if (typeword > TSUtils.TYPEWORD_MAX)
			throw new IllegalArgumentException("typeword (3 bytes) overflow");
		if (name.length() > 0xFF)
			throw new IllegalArgumentException("byte overflow (type name is to long)");
		this.typeword = typeword;
		this.name = name;

	}

	public int getTypeword() {
		return typeword;
	}

	public String getTypeName() {
		return name;
	}


	public abstract T valueOf(Object value);

	public abstract T defaultValue();

	public boolean isPrimitive() {
		return false;
	}

	public boolean isArray() {
		return this instanceof TSTypeArray<?>;
	}

	public boolean isPointer() {
		return this instanceof TSTypePointer;
	}

	public boolean isOneFieldType() {
		return true;
	}
}