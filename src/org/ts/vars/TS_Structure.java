package org.ts.vars;

import org.ts.*;
import org.ts.TSTypeCompound;

public class TS_Structure extends TSVar implements TSFieldContainer {

	private final TSVar[] values;
	private final TSTypeCompound type;

	public TS_Structure(TSTypeCompound type,Object... fieldValues) {
		this.type = type;
		this.values = new TSVar[type.getFields().length];
		for (int i = 0; i < values.length; i++) {
			if (i < fieldValues.length && fieldValues[i] != null) {
				if (fieldValues[i] instanceof TSVar) {
					values[i] = (TSVar) fieldValues[i];
				} else {
					values[i] = getType().getFields()[i].getType().valueOf(fieldValues[i]);
				}
			}
			else values[i] = getType().getFields()[i].getType().defaultValue();
		}

	}


	public void setValue(String field_name, Object value) {
		setValue(type.getFieldIndex(field_name),value);
	}

	public TSVar getValue(String field_name) {
		return values[type.getFieldIndex(field_name)];
	}


	@Override
	public int size() {
		int size = 0;
		for (TSVar value : values) size += value.size();
		return size;
	}

	public void setValue(Object value) {
		if (!getType().isOneFieldType()) throw new UnsupportedOperationException();
		setValue(0,value);
	}

	@Override
	public Object getValue() {
		if (!getType().isOneFieldType()) throw new UnsupportedOperationException();
		return getValue(getField(0));
	}



	@Override
	public TSTypeCompound getType() {
		return type;
	}

	@Override
	public void setValue(TSField field, Object value) {
		setValue(type.getFieldIndex(field),value);
	}
	private void setValue(int fieldIndex, Object value) {
		if (value instanceof TSVar) {
			if (((TSVar) value).getType() == getField(fieldIndex).getType()) values[fieldIndex] = (TSVar) value;
			else throw new CannotCastVarException();
		} else {
			values[fieldIndex] = getField(fieldIndex).getType().valueOf(value);
		} //else throw new CannotCastVarException();
	}
	private TSField getField(int index) {
		return getType().getFields()[index];
	}

	@Override
	public TSVar getValue(TSField field) {
		return values[type.getFieldIndex(field)];
	}

	@Override
	public String toString() {
		if (getType().isOneFieldType()) return values[0].toString();
		return super.toString();//todo
	}
}
