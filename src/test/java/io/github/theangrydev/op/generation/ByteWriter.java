package io.github.theangrydev.op.generation;

import io.github.theangrydev.op.generation.jvm.ClassFileWriter;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import static java.util.Arrays.stream;

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

	public static byte[] bytes(byte[]... byteArrays) {
		byte[] joined = new byte[stream(byteArrays).mapToInt(x -> x.length).sum()];
		int offset = 0;
		for (byte[] byteArray : byteArrays) {
			System.arraycopy(byteArray, 0, joined, offset, byteArray.length);
			offset += byteArray.length;
		}
		return joined;
	}
}
