package org.ts.types;

import org.ts.TSType;
import org.ts.vars.TS_Pointer;

import java.util.HashMap;

public class TSTypePointer extends TSType<TS_Pointer> {

	private static final HashMap<TSType<?>, TSTypePointer> targetTypes = new HashMap<>();

	private final TSType<?> target;

	private TSTypePointer(TSType<?> target) {
		super("__Pointer__", 0);
		this.target = target;
	}

	public static TSTypePointer forParent(TSType<?> target) {
		TSTypePointer type = targetTypes.get(target);
		if (type == null) {
			type = new TSTypePointer(target);
			targetTypes.put(target, type);
		}
		return type;
	}

	public TSType<?> getTargetType() {
		return target;
	}


	@Override
	public TS_Pointer valueOf(Object value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public TS_Pointer defaultValue() {
		return null;
	}

	@Override
	public int size() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getTypeName() {
		return "*"+target.getTypeName();
	}
}
