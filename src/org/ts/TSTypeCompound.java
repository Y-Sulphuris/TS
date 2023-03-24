package org.ts;

import org.ts.vars.TS_structure;

public final class TSTypeCompound extends TSType<TS_structure>{

	private final TSField[] fields;
	public TSField[] getFields() {
		return fields;
	}

	public TSField getField(int index) {
		try {
			return fields[index];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoFieldDefFoundException(e);
		}
	}
	public TSField getField(String name) {
		return fields[getFieldIndex(name)];
	}
	public int getFieldIndex(String name) {
		for (int i = 0; i< fields.length; i++) {
			if (fields[i].getName().equals(name)) return i;
		}
		throw new NoFieldDefFoundException();
	}
	TSTypeCompound(String name, int typeword, TSField... fields) {
		super(name, typeword);
		this.fields = fields;
	}
	@Override
	public int size() {
		int size = 4 + 1 + getTypeName().length();
		for (TSField field : fields) {
			size += field.size();
		}
		return size + 1;
	}

	@Override
	public TS_structure valueOf(Object value) {
		return null;
	}
}
