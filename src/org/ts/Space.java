package org.ts;

import org.ts.vars.TS_structure;

import java.util.HashMap;
import java.util.Properties;

public final class Space {
	private final Properties properties = new Properties();
	private final HashMap<String, TSComponent> components = new HashMap<>();
	private final HashMap<Integer, TSType<?>> definedTypes = new HashMap<>();


	public Space() {
		properties.put("pointer.size","4");
	}

	public String getProperty(String key) {
		return properties.getProperty(key,"0");
	}






	public TSType<?> defineType(String vts) {
		throw new UnsupportedOperationException(vts);
	}

	public synchronized TSType<?> defineType(String name, int typeword, TSField... fields) {
		if (definedTypes.containsKey(typeword) || components.containsKey(name))
			throw new IllegalArgumentException();

		TSType<?> type = new TSTypeCompound(name,typeword,fields);

		this.addComponent(name,type);
		definedTypes.put(typeword,type);

		return type;
	}
	public TSType<?> getType(int typeword) {
		return definedTypes.get(typeword);
	}
	public TSType<?> getType(String typename) {
		TSComponent component = components.get(typename);
		if (!(component instanceof TSType)) {
			component = OverrideTypes.get(typename);
		}
		return (TSType<?>)component;
	}

	public TSField newField(String typename, String name) {
		return newField(getType(typename),name);
	}

	public TSField newField(TSType<?> type, String name) {
		if (type == null) throw new NullPointerException();
		return new TSField(type,name);
	}




	private synchronized void addComponent(String name, TSComponent component) {
		if (components.containsKey(name))
			throw new IllegalArgumentException();

		components.put(name,component);
	}

	public byte[] getBytes() {
		return null;
	}

	public void defineVar(String name, TSType<?> type, Object... fieldValues) {
		if (type instanceof TSTypeCompound) {
			components.put(name,new TS_structure((TSTypeCompound) type,fieldValues));
		} else {
			if (fieldValues.length > 1 || fieldValues[0] == null) throw new IllegalArgumentException();
			Object value = fieldValues[0];
			components.put(name,type.valueOf(value));
		}
	}
}

