package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.generation.jvm.ByteValue;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.ByteValue.byteValue;

public class Istore1 implements Instruction {
	private static final ByteValue ISTORE1 = byteValue(0x3c);

	private Istore1() {
	}

	public static Istore1 istore1() {
		return new Istore1();
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		ISTORE1.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return ISTORE1.sizeInBytes();
	}
}
