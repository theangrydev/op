package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.generation.jvm.ByteValue;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.ByteValue.byteValue;

public class VoidReturn implements Instruction {
	private static final ByteValue RETURN = byteValue(0xb1);

	private VoidReturn() {}

	public static VoidReturn voidreturn() {
		return new VoidReturn();
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		RETURN.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return RETURN.sizeInBytes();
	}
}
