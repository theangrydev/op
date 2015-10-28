package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.generation.jvm.ByteValue;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.ByteValue.byteValue;

public class Ireturn implements Instruction {
	private static final ByteValue IRETURN = byteValue(0xac);

	private Ireturn() {}

	public static Ireturn ireturn() {
		return new Ireturn();
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		IRETURN.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return IRETURN.sizeInBytes();
	}
}
