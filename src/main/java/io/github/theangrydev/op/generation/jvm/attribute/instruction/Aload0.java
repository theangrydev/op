package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.generation.jvm.ByteValue;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.ByteValue.byteValue;

public class Aload0 implements Instruction {
	private static final ByteValue ALOAD0 = byteValue(0x2a);

	private Aload0() {}

	public static Aload0 aload0() {
		return new Aload0();
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		ALOAD0.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return ALOAD0.sizeInBytes();
	}
}
