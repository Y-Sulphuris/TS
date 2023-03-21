package org.ts;

public class TSUtils {
	public static final int TYPEWORD_MAX = 0x00FFFFFF;
	public static final int META_MAX = 0xFF;

	public static final int META_STRUCTURE = 0;
	public static final int META_TYPE = 1;
	public static final int META_POINTER = 2;
	public static final int META_ARRAY = 3;

	public static final int END_DEF = 0xFF;

	public static int getTypeword(int header) {
		return header & TYPEWORD_MAX;
	}
	public static int getMeta(int header) {
		return header & 0xFF00_0000;
	}
}