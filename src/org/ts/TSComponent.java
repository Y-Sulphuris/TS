package org.ts;


import java.nio.ByteBuffer;

public abstract class TSComponent {

	TSComponent() {}

	public final byte[] getBytes() {
		ByteBuffer buffer = ByteBuffer.allocate(size());
		return buffer.array();
	}
	public void getBytes(ByteBuffer buffer) {}

	public abstract int size();
}
