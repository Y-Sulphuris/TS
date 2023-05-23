package org.ts;


public interface TSFieldContainer {
	void setValue(TSField field, Object value);
	TSVar getValue(TSField field);

}