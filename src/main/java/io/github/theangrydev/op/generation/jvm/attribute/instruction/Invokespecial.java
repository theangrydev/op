package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.generation.jvm.ByteValue;
import io.github.theangrydev.op.generation.jvm.constant.ConstantPoolIndex;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.ByteValue.byteValue;

public class Invokespecial implements Instruction {

	private static final ByteValue INVOKESPECIAL = byteValue(0xb7);
	private final ConstantPoolIndex methodReference;

	private Invokespecial(ConstantPoolIndex methodReference) {
		this.methodReference = methodReference;
	}

	public static Invokespecial invokespecial(ConstantPoolIndex methodReference) {
		return new Invokespecial(methodReference);
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		INVOKESPECIAL.writeTo(dataOutput);
		methodReference.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return INVOKESPECIAL.sizeInBytes() + methodReference.sizeInBytes();
	}
}
