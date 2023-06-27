package org.ts.types;

import org.ts.TSType;
import org.ts.TSVar;
import org.ts.vars.TS_Array;

import java.util.HashMap;

public class TSTypeArray<T extends TSVar> extends TSType<TS_Array<T>> {
	/**
	 * All created array types (create automatically)
	 */
	private static final HashMap<TSType<?>, TSTypeArray<?>> targetTypes = new HashMap<>();

	/**
	 * Type of array elements
	 */
	private final TSType<T> target;

	private TSTypeArray(TSType<T> target) {
		super(target.getTypeName()+"[]", 0);
		this.target = target;
	}

	/**
	 * Find an array type for target, if it does not exist, create it and cache to {@link TSTypeArray#targetTypes}
	 * @param target type of array elements
	 * @return array type for given target type
	 */
	@SuppressWarnings("unchecked")
	public static <T extends TSVar> TSTypeArray<T> forParent(TSType<T> target) {
		TSTypeArray<T> type = (TSTypeArray<T>)targetTypes.get(target);
		if (type == null) {
			type = new TSTypeArray<>(target);
			targetTypes.put(target, type);
		}
		return type;
	}

	/**
	 * @return type of array elements
	 */
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
