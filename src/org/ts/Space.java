package org.ts;

import org.ts.vars.TS_Structure;
import org.ts.vts.TokenReader;

import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;
import java.util.Stack;

public final class Space implements TSFieldContainer {

	private int lastTypeword = 128;

	private final Properties properties = new Properties();
	private final HashMap<String, TSComponent> components = new HashMap<>();
	private final HashMap<Integer, TSType<?>> definedTypes = new HashMap<>();
	private final HashMap<TSField, TSVar> fieldValues = new HashMap<>();
	private final Stack<TSComponent> componentsOrder = new Stack<>();

	public Space() {
		properties.put("pointer.size","4");
	}

	public String getProperty(String key) {
		return properties.getProperty(key,"0");
	}







	public synchronized TSTypeCompound defineType(String name, int typeword, TSField... fields) {
		if (components.containsKey(name))
			throw new IllegalArgumentException("name \""+name+"\" was already taken");
		if (definedTypes.containsKey(typeword))
			throw new IllegalArgumentException("type ["+typeword+"] is already exist");


		TSTypeCompound type = new TSTypeCompound(name,typeword,fields);

		this.addComponent(name,type);
		definedTypes.put(typeword,type);
		lastTypeword = typeword;
		return type;
	}
	public TSType<?> getType(int typeword) throws NoTypeDefFoundException{
		TSType<?> type = definedTypes.get(typeword);
		if (type == null) throw new NoTypeDefFoundException(typeword);
		return type;
	}
	public TSType<?> getType(String typename) throws NoTypeDefFoundException{
		TSComponent component = components.get(typename);
		if (component == null) {
			component = OverriddenTypes.get(typename);
		} else if (!(component instanceof TSType)) {
			throw new NoTypeDefFoundException(typename);
		}

		return (TSType<?>)component;
	}





	private synchronized void addComponent(String name, TSComponent component) {
		if (components.containsKey(name))
			throw new IllegalArgumentException();

		components.put(name,component);
		componentsOrder.push(component);
	}

	public byte[] getBytes() {
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T extends TSVar> T defineVar(String name, TSType<T> type, Object... values) {
		if (type instanceof TSTypeCompound) {
			TSVar tsVar = new TS_Structure((TSTypeCompound) type,values);
			defineVar(name,tsVar);
			return (T)tsVar;
		} else {
			if (values.length > 1 || values[0] == null) throw new IllegalArgumentException();
			T tsVar = type.valueOf(values[0]);
			defineVar(name,tsVar);
			return tsVar;
		}
	}
	public  <T extends TSVar> T defineVar(String name,T value) {
		TSField field = new TSField(value.getType(),name);
		addComponent(name,field);
		setValue(field,value);
		return value;
	}

	public TSField getField(String name) {
		try {
			return (TSField) components.get(name);
		} catch (ClassCastException e) {
			throw new NoFieldDefFoundException(name);
		}
	}

	public void setValue(String field_name, Object value) {
		try {
			setValue((TSField) components.get(field_name),value);
		} catch (ClassCastException e) {
			throw new NoFieldDefFoundException(field_name);
		}
	}

	public boolean fieldExist(String field_name) {
		try {
			TSField field = (TSField) components.get(field_name);
			return field != null;
		} catch (Exception e) {
			return false;
		}
	}

	public TSVar getValue(String field_name) {
		try {
			TSField field = (TSField) components.get(field_name);
			Objects.requireNonNull(field);
			return getValue(field);
		} catch (Exception e) {
			throw new NoFieldDefFoundException(field_name);
		}
	}


	@Override
	public void setValue(TSField field, Object value) {
		Objects.requireNonNull(field);
		if (!components.containsValue(field)) throw new NoFieldDefFoundException(field.getName());
		if (value instanceof TSVar) {
			if (((TSVar) value).getType() == field.getType()) fieldValues.put(field,(TSVar) value);
			else throw new CannotCastVarException("cannot cast " + value + "to TSType " + field.getType());
		} else {
			fieldValues.put(field,field.getType().valueOf(value));
		}
	}

	@Override
	public TSVar getValue(TSField field) {
		Objects.requireNonNull(field);
		if (!components.containsValue(field)) throw new NoFieldDefFoundException(field.getName());
		return fieldValues.get(field);
	}










	//vts
	public void vts(String vts) {
		new TokenReader(vts).invoke(this,true);
	}


	public int nextFreeTypeword() {
		for (int i = lastTypeword; i < 0xFFFFFF; i++) {
			if (definedTypes.get(i) == null) {
				return i;
			}
		}
		throw new TypewordOverflowException();
	}





	@Override
	public String toString(Space this) {
		final StringBuilder builder = new StringBuilder("#head\n");
		builder.append("#defstart\n");

		TSComponent[] components = componentsOrder.toArray(new TSComponent[0]);
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof TSTypeCompound) {
				TSTypeCompound type = (TSTypeCompound) components[i];
				builder.append("type ").append("[").append(type.getTypeword()).append("] ").append(type.getTypeName()).append(" {\n");
				for (TSField currentField : type.getFields()) {
					builder.append("\t").append(currentField.getTypeName()).append(" ").append(currentField.getName()).append(";\n");
				}
				builder.append("};\n");
			} else if (components[i] instanceof TSField) {
				TSField field = (TSField) components[i];
				builder.append(fieldToString(field,this,""));
			}
		}

		return builder.toString();
	}


	private static String fieldToString(TSField field,TSFieldContainer container, String prefix) {
		StringBuilder builder = new StringBuilder();
		builder.append(prefix).append(container instanceof Space ? field.getType().getTypeName()+" " : "").append(field.getName()).append(" = ");
		if (field.getType() instanceof TSTypeCompound && ((TSTypeCompound) field.getType()).getFields().length != 1) {
			builder.append("{\n");
			for (TSField currentField : ((TSTypeCompound) field.getType()).getFields()) {
				try {
					TSFieldContainer varContainer = (TSFieldContainer)(field.get(container));
					builder.append(fieldToString(currentField, varContainer,prefix+"\t"));
				} catch (ClassCastException e) {
					throw new AssertionError(e);
				}
				//builder.append("\t").append(currentField.getName()).append(" = ").append(getValue(field)).append("\n");
			}
			builder.append(prefix).append("};\n");
		} else if (field.getType().isOneFieldType()) {
			builder.append(container.getValue(field).toString()).append(";\n");
		}

		return builder.toString();
	}
}

