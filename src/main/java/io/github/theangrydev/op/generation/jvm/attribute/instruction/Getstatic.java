package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.generation.jvm.ByteValue;
import io.github.theangrydev.op.generation.jvm.constant.ConstantPoolIndex;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.ByteValue.byteValue;

public class Getstatic implements Instruction {

	private static final ByteValue GETSTATIC = byteValue(0xb2);
	private final ConstantPoolIndex fieldReference;

	private Getstatic(ConstantPoolIndex fieldReference) {
		this.fieldReference = fieldReference;
	}

	public static Getstatic getstatic(ConstantPoolIndex fieldReference) {
		return new Getstatic(fieldReference);
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		GETSTATIC.writeTo(dataOutput);
		fieldReference.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return GETSTATIC.sizeInBytes() + fieldReference.sizeInBytes();
	}
}
