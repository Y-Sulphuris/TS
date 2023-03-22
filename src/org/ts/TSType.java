package org.ts;

public abstract class TSType extends TSComponent {

	private final int typeword;
	private final String name;


	public TSType(String name, int typeword) {
		super();
		if (typeword > TSUtils.TYPEWORD_MAX)
			throw new IllegalArgumentException("3 bytes overflow");
		if (name.length() > 0xFF)
			throw new IllegalArgumentException("Byte overflow");
		this.typeword = typeword;
		this.name = name;

	}

	public int getTypeword() {
		return typeword;
	}

	public String getName() {
		return name;
	}




	@Override
	public byte[] getBytes() {
		byte[] result = new byte[size()];
		return result;
	}

}
