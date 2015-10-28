package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.generation.jvm.ByteValue;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.ByteValue.byteValue;

public class Iconst3 implements Instruction {
	private static final ByteValue ICONST3 = byteValue(0x6);

	private Iconst3() {}

	public static Iconst3 iconst3() {
		return new Iconst3();
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		ICONST3.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return ICONST3.sizeInBytes();
	}
}
