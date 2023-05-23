package org.ts.types;

import org.ts.TSType;
import org.ts.TSVar;
import org.ts.vars.TS_Array;
import org.ts.vars.TS_Pointer;

import java.util.HashMap;

public class TSTypeArray<T extends TSVar> extends TSType<TS_Array<T>> {

	private static final HashMap<TSType<?>, TSTypeArray> targetTypes = new HashMap<>();

	private final TSType<T> target;

	private TSTypeArray(TSType<T> target) {
		super("__Array__("+target.getTypeName()+")", 0);
		this.target = target;
	}

	@SuppressWarnings("unchecked")
	public static <T extends TSVar> TSTypeArray<T> forParent(TSType<T> target) {
		TSTypeArray<T> type = targetTypes.get(target);
		if (type == null) {
			type = new TSTypeArray<>(target);
			targetTypes.put(target, type);
		}
		return type;
	}

	public TSType<?> getTargetType() {
		return target;
	}


	@Override
	public TS_Array<T> valueOf(Object value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public TS_Array<T> defaultValue() {
		return new TS_Array<T>(target,0);
	}

	@Override
	public int size() {
		throw new UnsupportedOperationException();
	}
}
