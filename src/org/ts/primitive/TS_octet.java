package org.ts.primitive;

public final class TS_octet extends org.ts.TSType {
	public static final int TYPEWORD = 1;
	public TS_octet() {
		super("octet", TYPEWORD);
	}

	@Override
	public int size() {
		return 1;
	}
}
