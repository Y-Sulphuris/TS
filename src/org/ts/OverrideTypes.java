package org.ts;

import org.ts.primitive.*;

import java.util.HashMap;

public final class OverrideTypes {
	public static final TSType[] types = new TSType[128];
	public static final HashMap<String, TSType> typeNames = new HashMap<>();
	static {
		registerType(new TSType_octet());//1
		registerType(new TSType_word());//2
		registerType(new TSType_dword());//3
		registerType(new TSType_fword());//4
		registerType(new TSType_qword());//5
		registerType(new TSType_tword());//6
		registerType(new TSType_dqword());//7

		registerOneFieldCompoundType("byte",                17, TSType_octet.TYPEWORD);
		registerOneFieldCompoundType("unsigned_byte",       18, TSType_octet.TYPEWORD);
		registerOneFieldCompoundType("short",               19, TSType_word.TYPEWORD);
		registerOneFieldCompoundType("unsigned_short",      20, TSType_word.TYPEWORD);
		registerOneFieldCompoundType("int",                 21, TSType_dword.TYPEWORD);
		registerOneFieldCompoundType("unsigned_int",        22, TSType_dword.TYPEWORD);
		registerOneFieldCompoundType("long",                23, TSType_qword.TYPEWORD);
		registerOneFieldCompoundType("unsigned_long",       24, TSType_qword.TYPEWORD);
		registerOneFieldCompoundType("float",               25, TSType_dword.TYPEWORD);
		registerOneFieldCompoundType("double",              26, TSType_qword.TYPEWORD);
		registerOneFieldCompoundType("long_long",           27, TSType_dqword.TYPEWORD);
		registerOneFieldCompoundType("unsigned_long_long",  28, TSType_dqword.TYPEWORD);
		registerOneFieldCompoundType("long_double",         29, TSType_tword.TYPEWORD);
		registerOneFieldCompoundType("decimal",             30, TSType_dqword.TYPEWORD);
		registerOneFieldCompoundType("achar",               31, TSType_octet.TYPEWORD);
		registerOneFieldCompoundType("char8",               32, TSType_octet.TYPEWORD);
		registerOneFieldCompoundType("char16",              33, TSType_word.TYPEWORD);
		registerCompoundType(        "astring",             34,new TSField(get(31),""));
		registerCompoundType(        "string8",             35,new TSField(get(32),""));
		registerCompoundType(        "string16",            36,new TSField(get(33),""));
	}
	private static void registerType(TSType type) {
		types[type.getTypeword()] = type;
		typeNames.put(type.getTypeName(),type);
	}
	private static void registerCompoundType(String name, int typeword, TSField... fields) {
		registerType(new TSTypeCompound(name,typeword,fields));
	}
	private static void registerOneFieldCompoundType(String name, int typeword, int target_typeword) {
		registerCompoundType(name,typeword,new TSField(get(target_typeword),""));
	}
	public static TSType get(int typeword) {
		TSType type = types[typeword];
		if (type == null) throw new NoTypeDefFoundException(typeword);
		return type;
	}
	public static TSType get(String name) {
		TSType type = typeNames.get(name);
		if (type == null) throw new NoTypeDefFoundException(name);
		return type;
	}


}
