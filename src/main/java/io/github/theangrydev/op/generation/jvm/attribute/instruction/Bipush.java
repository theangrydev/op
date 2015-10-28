package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.generation.jvm.ByteValue;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.ByteValue.byteValue;

public class Bipush implements Instruction {

	private static final ByteValue BIPUSH = byteValue(0x10);
	private final ByteValue byteToPush;

	private Bipush(ByteValue byteToPush) {
		this.byteToPush = byteToPush;
	}

	public static Bipush bipush(ByteValue byteToPush) {
		return new Bipush(byteToPush);
	}

	public static Bipush bipush(int byteToPush) {
		return bipush(byteValue(byteToPush));
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		BIPUSH.writeTo(dataOutput);
		byteToPush.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return BIPUSH.sizeInBytes() + byteToPush.sizeInBytes();
	}
}
