package org.ts.vars;

import org.ts.*;
import org.ts.types.TSTypePointer;

public final class TS_Pointer extends TSVar {

	private final TSField target;
	private final TSFieldContainer container;

	private final TSTypePointer type;

	public TS_Pointer(TSFieldContainer container, TSField target) {
		this.target = target;
		this.container = container;
		type = TSTypePointer.forParent(target.getType());
	}

	public TSField getTarget() {
		return target;
	}
	public TSFieldContainer getContainer() {
		return container;
	}



	@Override
	public int size() {
		throw new UnknownSizeException();
	}

	@Override
	public Object getValue() {
		return container.getValue(target);
	}

	@Override
	public TSTypePointer getType() {
		return type;
	}


	@Override
	public String toString() {
		if (container instanceof Space) {
			return "&"+target;
		} else {
			return "&"+container+"."+target.getName();
		}
	}
}