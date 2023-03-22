package org.ts;

import org.ts.primitive.*;

import java.util.HashMap;

public final class OverrideTypes {
	public static final HashMap<Integer, TSType> types = new HashMap<>();
	static {
		registerType(new TS_octet());//1
		registerType(new TS_word());//2
		registerType(new TS_dword());//3
		registerType(new TS_fword());//4
		registerType(new TS_qword());//5
		registerType(new TS_tword());//6
		registerType(new TS_dqword());//7

		registerOneFieldCompoundType("byte",17,TS_octet.TYPEWORD);
		registerOneFieldCompoundType("unsigned_byte",18,TS_octet.TYPEWORD);
		registerOneFieldCompoundType("short",19,TS_word.TYPEWORD);
		registerOneFieldCompoundType("unsigned_short",20,TS_word.TYPEWORD);
		registerOneFieldCompoundType("int",21,TS_dword.TYPEWORD);
		registerOneFieldCompoundType("unsigned_int",22,TS_dword.TYPEWORD);
		registerOneFieldCompoundType("long",23,TS_qword.TYPEWORD);
		registerOneFieldCompoundType("unsigned_long",24,TS_qword.TYPEWORD);
		registerOneFieldCompoundType("float",25,TS_dword.TYPEWORD);
		registerOneFieldCompoundType("double",26,TS_qword.TYPEWORD);
		registerOneFieldCompoundType("long_long",27,TS_dqword.TYPEWORD);
		registerOneFieldCompoundType("unsigned_long_long",28,TS_dqword.TYPEWORD);
		registerOneFieldCompoundType("long_double",29,TS_tword.TYPEWORD);
		registerOneFieldCompoundType("decimal",30,TS_dqword.TYPEWORD);
		registerOneFieldCompoundType("achar",31,TS_octet.TYPEWORD);
		registerOneFieldCompoundType("char8",32,TS_octet.TYPEWORD);
		registerOneFieldCompoundType("char16",33,TS_word.TYPEWORD);
		registerCompoundType("astring",34,new TSField(31,new byte[]{TSUtils.META_ARRAY,0},""));
		registerCompoundType("string8",35,new TSField(32,new byte[]{TSUtils.META_ARRAY,0},""));
		registerCompoundType("string16",36,new TSField(33,new byte[]{TSUtils.META_ARRAY,0},""));
	}
	private static void registerType(TSType type) {
		types.put(type.getTypeword(),type);
	}
	private static void registerCompoundType(String name, int typeword, TSField... fields) {
		registerType(new TSTypeCompound(name,typeword,fields));
	}
	private static void registerOneFieldCompoundType(String name, int typeword, int target_typeword) {
		registerCompoundType(name,typeword,new TSField(target_typeword,""));
	}
}
