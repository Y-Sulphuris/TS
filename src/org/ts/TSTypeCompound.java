package org.ts;

public class TSTypeCompound extends TSType{
	private final TSField[] fields;
	public TSField getField(int index) {
		return fields[index];
	}
	public TSField getField(String name) {
		for (TSField field : fields)
			if (field.getName().equals(name)) return field;
		throw new NoFieldDefFoundException();
	}
	TSTypeCompound(String name, int typeword, TSField... fields) {
		super(name, typeword);
		this.fields = fields;
	}
	@Override
	public int size() {
		int size = 4 + 1 + getName().length();
		for (TSField field : fields) {
			size += field.size();
		}
		return size + 1;
	}
}
