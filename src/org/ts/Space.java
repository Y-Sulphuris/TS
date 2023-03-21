package org.ts;

import java.util.HashMap;
import java.util.Properties;

public class Space {
	private final Properties properties = new Properties();
	private final HashMap<String, TSComponent> components = new HashMap<>();
	private final HashMap<Integer, TSType> definedTypes = new HashMap<>();

	public synchronized void defineType(String name, int typeword, String[] fieldNames, TSType[] fieldTypes) {
		if (definedTypes.containsKey(typeword))
			throw new IllegalArgumentException();
		if (fieldNames.length != fieldTypes.length)
			throw new IllegalArgumentException();

		TSType type = new TSType(this,name,typeword);
		for (int i = 0; i < fieldNames.length; i++) {
			type.addField(fieldNames[i],fieldTypes[i]);
		}
		this.addComponent(name,type);
		definedTypes.put(typeword,type);
	}
	synchronized void addComponent(String name, TSComponent component) {
		if (components.containsKey(name))
			throw new IllegalArgumentException();

		components.put(name,component);
	}

	public byte[] getBytes() {
		return null;
	}
}

