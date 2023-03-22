package org.ts;

public final class TSField extends TSComponent{


	private final String name;
	private final TSHeader header;

	public String getName() {
		return name;
	}
	public TSHeader getHeader() {
		return header;
	}

	TSField(int typeword, String name) {
		this(new TSHeader(typeword,new byte[]{0}),name);
	}
	TSField(int typeword, byte[] meta, String name) {
		this(new TSHeader(typeword,meta),name);
	}
	TSField(TSHeader header, String name) {
		this.header = header;
		this.name = name;
	}


	@Override
	public byte[] getBytes() {
		return new byte[0];
	}

	@Override
	public int size() {
		return header.size() + name.length() + 1;
	}
}
