package org.ts;

import org.ts.vars.TS_Structure;

public final class TSTypeCompound extends TSType<TS_Structure> {

	private final TSField[] fields;
	private final Signature vtsSignature;

	public TSField[] getFields() {
		return fields;
	}

	public TSField getField(int index) throws NoFieldDefFoundException {
		try {
			return fields[index];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoFieldDefFoundException(e);
		}
	}
	public TSField getField(String name) throws NoFieldDefFoundException {
		return fields[getFieldIndex(name)];
	}
	public int getFieldIndex(String field_name) throws NoFieldDefFoundException {
		for (int i = 0; i< fields.length; i++) {
			if (fields[i].getName().equals(field_name)) return i;
		}
		throw new NoFieldDefFoundException(field_name);
	}
	public int getFieldIndex(TSField field) {
		for (int i = 0; i< fields.length; i++) {
			if (fields[i] == field) return i;
		}
		throw new NoFieldDefFoundException(field.getName());
	}
	TSTypeCompound(String name, int typeword,Signature signature, TSField... fields) {
		super(name, typeword);
		this.fields = fields;
		this.vtsSignature = signature;
	}
	TSTypeCompound(String name, int typeword, TSField... fields) {
		this(name, typeword,null, fields);
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
	public TS_Structure valueOf(Object value) {
		if (vtsSignature != null && value instanceof String)
			return vtsSignature.valueOf((String)value);

		return new TS_Structure(this,value);
	}

	@Override
	public TS_Structure defaultValue() {
		TS_Structure structure = new TS_Structure(this);
		for (TSField field : fields) {
			structure.setValue(field.getType().defaultValue());
		}
		return structure;
	}

	@Override
	public boolean isOneFieldType() {
		return fields.length == 1;
	}
}
