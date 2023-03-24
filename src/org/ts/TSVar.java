package org.ts;

public abstract class TSVar extends TSComponent implements Cloneable{
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
}

