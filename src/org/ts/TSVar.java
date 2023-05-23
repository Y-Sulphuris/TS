package org.ts;

public abstract class TSVar implements Cloneable{
	public abstract Object getValue();

	@Override
	public TSVar clone() {
		try {
			return (TSVar) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError(e);
		}
	}

	public abstract TSType getType();

	public abstract int size();


	@Override
	public String toString() {
		if (getType().isPrimitive()) return String.valueOf(getValue());
		return super.toString();
	}
}

