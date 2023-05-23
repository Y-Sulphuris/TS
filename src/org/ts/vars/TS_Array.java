package org.ts.vars;

import org.ts.OverrideTypes;
import org.ts.TSType;
import org.ts.TSTypeCompound;
import org.ts.types.*;
import org.ts.TSVar;

public class TS_Array<T extends TSVar> extends TSVar {

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
	public TS_Array(TSType<T> type, T... elements) {
		targetType = type;
		this.elements = elements;
		lengthSize = length() > 0xFF ? (length() > 0xFFFF ? 4 : 2) : 1;
		this.type = TSTypeArray.forParent(type);
	}
	@SuppressWarnings("unchecked")
	public TS_Array(TSType<T> type, int length) {
		this(type,(T[])new TSVar[length]);
	}

	private final int lengthSize;
	int getLengthSize() {
		return lengthSize;
	}

	@Override
	public int size() {
		int size = getLengthSize();
		for (T element : elements)
			size += element.size();
		return size;
	}

	@Override
	public Object getValue() {
		return elements;
	}




	public static TS_Array<TS_octet> valueOfPrimitive(byte[] value) {
		final int length = value.length;
		TSType_octet type = (TSType_octet) OverrideTypes.get("octet");
		TS_octet[] array = new TS_octet[length];
		for (int i = 0; i < length; i++) {
			array[i] = type.valueOf(value[i]);
		}
		return new TS_Array<TS_octet>(type, array);
	}
	public static TS_Array<TS_word> valueOfPrimitive(short[] value) {
		final int length = value.length;
		TSType_word type = (TSType_word) OverrideTypes.get("word");
		TS_word[] array = new TS_word[length];
		for (int i = 0; i < length; i++) {
			array[i] = type.valueOf(value[i]);
		}
		return new TS_Array<>(type, array);
	}
	public static TS_Array<TS_dword> valueOfPrimitive(int[] value) {
		final int length = value.length;
		TSType_dword type = (TSType_dword) OverrideTypes.get("dword");
		TS_dword[] array = new TS_dword[length];
		for (int i = 0; i < length; i++) {
			array[i] = type.valueOf(value[i]);
		}
		return new TS_Array<>(type, array);
	}
	public static TS_Array<TS_qword> valueOfPrimitive(long[] value) {
		final int length = value.length;
		TSType_qword type = (TSType_qword) OverrideTypes.get("qword");
		TS_qword[] array = new TS_qword[length];
		for (int i = 0; i < length; i++) {
			array[i] = type.valueOf(value[i]);
		}
		return new TS_Array<>(type, array);
	}


	public static TS_Array<TS_Structure> valueOf(byte[] value) {
		return valueOf(value,true);
	}
	public static TS_Array<TS_Structure> valueOf(byte[] value, boolean signed) {
		final int length = value.length;
		TSTypeCompound type = (TSTypeCompound) OverrideTypes.get(signed ? "byte" : "unsigned_byte");
		TS_Structure[] array = new TS_Structure[length];
		for (int i = 0; i < length; i++) {
			array[i] = type.valueOf(value[i]);
		}
		return new TS_Array<>(type, array);
	}


	public static TS_Array<TS_Structure> valueOf(short[] value) {
		return valueOf(value,true);
	}
	public static TS_Array<TS_Structure> valueOf(short[] value, boolean signed) {
		final int length = value.length;
		TSTypeCompound type = (TSTypeCompound) OverrideTypes.get(signed ? "short" : "unsigned_short");
		TS_Structure[] array = new TS_Structure[length];
		for (int i = 0; i < length; i++) {
			array[i] = type.valueOf(value[i]);
		}
		return new TS_Array<>(type, array);
	}

	public static TS_Array<TS_Structure> valueOf(int[] value) {
		return valueOf(value,true);
	}
	public static TS_Array<TS_Structure> valueOf(int[] value, boolean signed) {
		final int length = value.length;
		TSTypeCompound type = (TSTypeCompound) OverrideTypes.get(signed ? "int" : "unsigned_int");
		TS_Structure[] array = new TS_Structure[length];
		for (int i = 0; i < length; i++) {
			array[i] = type.valueOf(value[i]);
		}
		return new TS_Array<>(type, array);
	}

	public static TS_Array<TS_Structure> valueOf(long[] value) {
		return valueOf(value,true);
	}
	public static TS_Array<TS_Structure> valueOf(long[] value, boolean signed) {
		final int length = value.length;
		TSTypeCompound type = (TSTypeCompound) OverrideTypes.get(signed ? "long" : "unsigned_long");
		TS_Structure[] array = new TS_Structure[length];
		for (int i = 0; i < length; i++) {
			array[i] = type.valueOf(value[i]);
		}
		return new TS_Array<>(type, array);
	}
	public static TS_Array<TS_Structure> valueOf(float[] value) {
		final int length = value.length;
		TSTypeCompound type = (TSTypeCompound) OverrideTypes.get("float");
		TS_Structure[] array = new TS_Structure[length];
		for (int i = 0; i < length; i++) {
			array[i] = type.valueOf(value[i]);
		}
		return new TS_Array<>(type, array);
	}
	public static TS_Array<TS_Structure> valueOf(double[] value) {
		final int length = value.length;
		TSTypeCompound type = (TSTypeCompound) OverrideTypes.get("double");
		TS_Structure[] array = new TS_Structure[length];
		for (int i = 0; i < length; i++) {
			array[i] = type.valueOf(value[i]);
		}
		return new TS_Array<>(type, array);
	}

	@Override
	public TSType<?> getType() {
		return type;
	}
	
	private final TSTypeArray<T> type;

}
