package org.ts;

import java.lang.annotation.Native;


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


	@Native
	private static final int USER_TYPES_LIMIT = 16777088; //0xFFFFFF-127




	public Class<?> getCallerClass() {
		try {
			return Class.forName(getCallerClassName());
		} catch (ClassNotFoundException e) {
			throw new AssertionError(e);
		}
	}
	private String getCallerClassName() {
		return new Throwable().getStackTrace()[0].getClassName();
	}

	@SuppressWarnings("unchecked")
	public <E extends Throwable> void throwException(__unsafe__ this, Throwable e) throws E {
		throw (E) e;
	}

	public byte[] concat(byte[] one, byte... two) {
		byte[] res = new byte[one.length + two.length];
		try {
			System.arraycopy(one, 0, res, 0, one.length);
			System.arraycopy(two, 0, res, one.length, two.length);
		}catch (ArrayIndexOutOfBoundsException e) {
			throw new AssertionError(e);
		}
		return res;
	}

	public boolean assertion(boolean b) {
		if (!b) throw new AssertionError();
		return b;
	}

}