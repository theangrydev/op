package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.generation.jvm.ByteValue;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.ByteValue.byteValue;

public class Iconst2 implements Instruction {
	private static final ByteValue ICONST2 = byteValue(0x5);

	private Iconst2() {}

	public static Iconst2 iconst2() {
		return new Iconst2();
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		ICONST2.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return ICONST2.sizeInBytes();
	}
}
