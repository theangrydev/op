package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.generation.jvm.ByteValue;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.ByteValue.byteValue;

public class Iadd implements Instruction {
	private static final ByteValue IADD = byteValue(0x60);

	private Iadd() {}

	public static Iadd iadd() {
		return new Iadd();
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		IADD.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return IADD.sizeInBytes();
	}
}
