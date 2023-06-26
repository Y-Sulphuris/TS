package org.ts;


import java.nio.ByteBuffer;

public abstract class TSComponent {

	TSComponent() {}

	public final byte[] getBytes() {
		ByteBuffer buffer = ByteBuffer.allocate(size());
		return buffer.array();
	}
	//throws UnsafeLinkError if it's overridden
	public native void getBytes(ByteBuffer buffer);

	public abstract int size();
}
