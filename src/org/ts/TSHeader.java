package org.ts;

public final class TSHeader {
	private final int typeword;
	private final byte[] meta;

	public int getTypeword() {
		return typeword;
	}
	public byte[] getMeta() {
		return meta.clone();
	}

	public TSHeader(int typeword, byte[] meta) {
		this.typeword = typeword;
		this.meta = meta;
	}
	public TSHeader(int typeword, int... meta) {
		this(typeword,intsToBytes(meta));
	}

	private static byte[] intsToBytes(int[] array) {
		byte[] result = new byte[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = (byte)array[i];
		}
		return result;
	}

	public int size() {
		return 3 + meta.length;
	}
}
