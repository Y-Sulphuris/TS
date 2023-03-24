package org.ts.vars;

import org.ts.TSTypeCompound;
import org.ts.TSVar;

public class TS_structure extends TSVar {

	private final TSVar[] values;
	private final TSTypeCompound type;


	public TS_structure(TSTypeCompound type, Object... fieldValues) {
		this.type = type;
		this.values = new TSVar[type.getFields().length];
		for (int i = 0; i < fieldValues.length; i++) {
			values[i] = getType().getFields()[i].getType().valueOf(fieldValues);
		}
	}


	public void setValue(String field_name, Object value) {
		int index = type.getFieldIndex(field_name);

		if (value instanceof TSVar) {
			values[index] = (TSVar) value;
		} else {
			values[index] = values[index].getType().valueOf(value);
		}

	}

	public Object getValue(String field_name) {
		return values[type.getFieldIndex(field_name)];
	}


	@Override
	public int size() {
		int size = 0;
		for (TSVar value : values) size += value.size();
		return size;
	}

	public void setValue(Object value) {
		setValue("",value);
	}

	@Override
	public Object getValue() {
		return getValue("");
	}



	@Override
	public TSTypeCompound getType() {
		return type;
	}

}
