package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.generation.jvm.ByteValue;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.ByteValue.byteValue;

public class Istore2 implements Instruction {
	private static final ByteValue ISTORE2 = byteValue(0x3d);

	private Istore2() {}

	public static Istore2 istore2() {
		return new Istore2();
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		ISTORE2.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return ISTORE2.sizeInBytes();
	}
}
