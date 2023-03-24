package org.ts;

public final class __unsafe__ {

	private static final __unsafe__ the_one = new __unsafe__();

	public static __unsafe__ getUnsafe() {
		SecurityException exception = new SecurityException("Unsafe");
		if (!exception.getStackTrace()[1].getClassName().startsWith("org.ts."))
			throw exception;
		return the_one;
	}

	private __unsafe__() {
		if (the_one != null)
			throw new IllegalAccessError();
	}



	private static final int USER_TYPES_LIMIT = 16777088; //0xFFFFFF-127


}
