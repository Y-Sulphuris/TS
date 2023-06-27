package org.ts;

import org.ts.types.*;
import org.ts.vars.TS_Structure;

import java.math.BigInteger;
import java.util.HashMap;

public final class OverriddenTypes {
	public static final TSType<?>[] types = new TSType[128];
	public static final HashMap<String, TSType<?>> typeNames = new HashMap<>();

	public static final Signature signature_byte = (str) -> {
		if (str.startsWith("0x")) return newStructure("byte",str);
		try {
			if (str.endsWith("b")) {
				str = str.substring(0,str.length()-1);
				return newStructure("byte",Byte.parseByte(str));
			} else {
				return newStructure("byte", new BigInteger(str));
			}
		} catch (Exception e) {
			throw new CannotCastVarException(e);
		}
	};
	public static final Signature signature_unsigned_byte = (str) -> {
		if (str.startsWith("0x")) return newStructure("unsigned_byte",str);
		try {
			if (str.endsWith("b")) {
				str = str.substring(0,str.length()-1);
				if (str.startsWith("u")) {
					str = str.substring(1);
					int value = Integer.parseInt(str);
					if (value < 0 || value > 255) throw new UnexpectedTokenException("unsigned byte overflow");
					return newStructure("unsigned_byte",value);
				} else throw new UnexpectedTokenException("'u' expected");
			} else {
				return newStructure("unsigned_byte", new BigInteger(str));
			}
		} catch (Exception e) {
			throw new CannotCastVarException(e);
		}
	};


	private static TS_Structure newStructure(String typename, Object... fieldValues) {
		return new TS_Structure((TSTypeCompound) OverriddenTypes.get(typename),fieldValues);
	}







	static {
		registerType(new TSType_octet());//1
		registerType(new TSType_word());//2
		registerType(new TSType_dword());//3
		registerType(new TSType_fword());//4
		registerType(new TSType_qword());//5
		registerType(new TSType_tword());//6
		registerType(new TSType_dqword());//7

		registerOneFieldCompoundType("byte",                17, TSType_octet.TYPEWORD,signature_byte);
		registerOneFieldCompoundType("unsigned_byte",       18, TSType_octet.TYPEWORD,signature_unsigned_byte);
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
		registerCompoundType(        "astring",             34,new TSField(get(31),""));//todo: make it array
		registerCompoundType(        "string8",             35,new TSField(get(32),""));
		registerCompoundType(        "string16",            36,new TSField(get(33),""));
	}
	private static void registerType(TSType<?> type) {
		types[type.getTypeword()] = type;
		typeNames.put(type.getTypeName(),type);
	}
	private static void registerCompoundType(String name, int typeword, TSField... fields) {
		registerCompoundType(name, typeword,null, fields);
	}
	private static void registerCompoundType(String name, int typeword,Signature signature, TSField... fields) {
		registerType(new TSTypeCompound(name,typeword,signature,fields));
	}
	private static void registerOneFieldCompoundType(String name, int typeword, int target_typeword) {
		registerOneFieldCompoundType(name, typeword, target_typeword,null);
	}
	private static void registerOneFieldCompoundType(String name, int typeword, int target_typeword, Signature signature) {
		registerCompoundType(name,typeword,signature,new TSField(get(target_typeword),""));
	}
	public static TSType get(int typeword) {
		TSType<?> type = types[typeword];
		if (type == null) throw new NoTypeDefFoundException(typeword);
		return type;
	}
	public static TSType get(String name) throws NoTypeDefFoundException{
		TSType<?> type = typeNames.get(name);
		if (type == null) throw new NoTypeDefFoundException(name);
		return type;
	}




	public static final TSType_octet    octet  = (TSType_octet) get(TSType_octet.TYPEWORD);
	public static final TSType_word     word   = (TSType_word)  get(TSType_word.TYPEWORD);
	public static final TSType_dword    dword  = (TSType_dword) get(TSType_dword.TYPEWORD);
	public static final TSType_fword    fword  = (TSType_fword) get(TSType_fword.TYPEWORD);
	public static final TSType_qword    qword  = (TSType_qword) get(TSType_qword.TYPEWORD);
	public static final TSType_tword    tword  = (TSType_tword) get(TSType_tword.TYPEWORD);
	public static final TSType_dqword   dqword = (TSType_dqword) get(TSType_dqword.TYPEWORD);

	public static final TSTypeCompound
			ts_byte = (TSTypeCompound) get(17),
			ts_unsigned_byte = (TSTypeCompound) get(18),
			ts_short = (TSTypeCompound) get(19),
			ts_unsigned_short = (TSTypeCompound) get(20),
			ts_int = (TSTypeCompound) get(21),
			ts_unsigned_int = (TSTypeCompound) get(22),
			ts_long = (TSTypeCompound) get(23),
			ts_unsigned_long = (TSTypeCompound) get(24),
			ts_float = (TSTypeCompound) get(25),
			ts_double = (TSTypeCompound) get(26),
			ts_long_long = (TSTypeCompound) get(27),
			ts_unsigned_long_long = (TSTypeCompound) get(28),
			ts_long_double = (TSTypeCompound) get(29),
			ts_decimal = (TSTypeCompound) get(30),
			ts_achar = (TSTypeCompound) get(31),
			ts_char8 = (TSTypeCompound) get(32),
			ts_char16 = (TSTypeCompound) get(33),
			ts_astring = (TSTypeCompound) get(34),
			ts_string8 = (TSTypeCompound) get(35),
			ts_string16 = (TSTypeCompound) get(36);
}
