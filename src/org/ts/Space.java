package org.ts;

import java.util.HashMap;
import java.util.Properties;

public final class Space {
	private final Properties properties = new Properties();
	private final HashMap<String, TSComponent> components = new HashMap<>();
	private final HashMap<Integer, TSType> definedTypes = new HashMap<>();


	public Space() {
		properties.put("pointer.size","4");
		properties.put("array.length.size","4");
	}

	public String getProperty(String key) {
		return properties.getProperty(key,"0");
	}






	public TSType defineType(String vts) {
		throw new UnsupportedOperationException(vts);
	}

	public synchronized TSType defineType(String name, int typeword, TSField... fields) {
		if (definedTypes.containsKey(typeword) || components.containsKey(name))
			throw new IllegalArgumentException();

		TSType type = new TSTypeCompound(name,typeword,fields);

		this.addComponent(name,type);
		definedTypes.put(typeword,type);

		return type;
	}
	public TSType getType(int typeword) {
		return definedTypes.get(typeword);
	}
	public TSType getType(String typename) {
		TSComponent component = components.get(typename);
		if (!(component instanceof TSType)) {
			throw new NoTypeDefFoundException();
		}
		return (TSType)component;
	}

	public TSField newField(String meta, String typename, String name) {
		return newField(meta,getType(typename),name);
	}

	public TSField newField(String meta, TSType type, String name) {
		if (type == null) throw new NullPointerException();
		meta = meta.replace("[]","A");
		byte[] bmeta = new byte[meta.length()+1];
		for (int i = 0; i < name.length(); i++) {
			if (meta.charAt(i) == '*') bmeta[i] = TSUtils.META_POINTER;
			else if (meta.charAt(i) == 'A') bmeta[i] = TSUtils.META_ARRAY;
		}
		bmeta[meta.length()] = 0;
		return new TSField(type.getTypeword(),bmeta,name);
	}




	private synchronized void addComponent(String name, TSComponent component) {
		if (components.containsKey(name))
			throw new IllegalArgumentException();

		components.put(name,component);
	}

	public byte[] getBytes() {
		return null;
	}
}

