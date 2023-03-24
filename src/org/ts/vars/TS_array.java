package org.ts.vars;

import org.ts.TSType;
import org.ts.TSVar;

public class TS_array<T extends TSVar> extends TSVar {

	private final T[] elements;
	private final TSType<T> targetType;
	public T[] getElements() {
		return elements;
	}
	public TSType<T> getTargetType() {
		return targetType;
	}

	public int length() {
		return elements.length;
	}

	@SafeVarargs
	public TS_array(TSType<T> type, T... elements) {
		targetType = type;
		this.elements = elements;
	}
	public TS_array(TSType<T> type, int length) {
		targetType = type;
		elements = (T[])new TSVar[length];
	}

	int getLengthSize() {
		int length = length();
		return length > 0xFF ? (length > 0xFFFF ? 4 : 2) : 1;
	}

	@Override
	public int size() {
		int size = 0;
		return 0;
	}

	@Override
	public Object getValue() {
		return elements;
	}

	@Override
	public TSType<T> getType() {
		throw new UnsupportedOperationException();
	}
}
