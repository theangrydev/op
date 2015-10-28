package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.generation.jvm.ByteValue;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.ByteValue.byteValue;

public class Iload0 implements Instruction {
	private static final ByteValue ILOAD0 = byteValue(0x1a);

	private Iload0() {}

	public static Iload0 iload0() {
		return new Iload0();
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		ILOAD0.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return ILOAD0.sizeInBytes();
	}
}
