package io.github.theangrydev.op.generation;

import io.github.theangrydev.op.generation.jvm.ClassFileWriter;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ByteWriter {

	public static byte[] bytesWrittenBy(ClassFileWriter classFileWriter) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		classFileWriter.writeTo(new DataOutputStream(out));
		return out.toByteArray();
	}

	public static byte[] bytes(byte first, byte... rest) {
		byte[] joined = new byte[rest.length + 1];
		joined[0] = first;
		System.arraycopy(rest, 0, joined, 1, rest.length);
		return joined;
	}
}
