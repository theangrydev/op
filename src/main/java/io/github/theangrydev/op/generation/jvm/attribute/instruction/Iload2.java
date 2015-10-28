package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.generation.jvm.ByteValue;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.ByteValue.byteValue;

public class Iload2 implements Instruction {
	private static final ByteValue ILOAD2 = byteValue(0x1c);

	private Iload2() {}

	public static Iload2 iload2() {
		return new Iload2();
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		ILOAD2.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return ILOAD2.sizeInBytes();
	}
}
