package org.ts;

/**
 * Any that can contains {@link TSField} as part of payload
 */
public interface TSFieldContainer {
	void setValue(TSField field, Object value);
	TSVar getValue(TSField field);

}