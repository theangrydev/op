package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.generation.jvm.ByteValue;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.ByteValue.byteValue;

public class Iload1 implements Instruction {
	private static final ByteValue ILOAD1 = byteValue(0x1b);

	private Iload1() {}

	public static Iload1 iload1() {
		return new Iload1();
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		ILOAD1.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return ILOAD1.sizeInBytes();
	}
}
