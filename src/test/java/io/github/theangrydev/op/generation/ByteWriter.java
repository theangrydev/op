package io.github.theangrydev.op.generation;

import io.github.theangrydev.op.generation.jvm.ClassFileWriter;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ByteWriter {

	public static byte[] bytesWrittenBy(ClassFileWriter... classFileWriters) throws IOException {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream dataOutput = new DataOutputStream(byteStream);
		for (ClassFileWriter classFileWriter : classFileWriters) {
			classFileWriter.writeTo(dataOutput);
		}
		return byteStream.toByteArray();
	}

	public static byte[] bytes(byte first, byte... rest) {
		return bytes(new byte[]{first}, rest);
	}

	public static byte[] bytes(byte[] first, byte[] second) {
		byte[] joined = new byte[first.length + second.length];
		System.arraycopy(first, 0, joined, 0, first.length);
		System.arraycopy(second, 0, joined, first.length, second.length);
		return joined;
	}
}
