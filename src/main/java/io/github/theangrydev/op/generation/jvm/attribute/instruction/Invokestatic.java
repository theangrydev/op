package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.generation.jvm.ByteValue;
import io.github.theangrydev.op.generation.jvm.constant.ConstantPoolIndex;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.ByteValue.byteValue;

public class Invokestatic implements Instruction {

	private static final ByteValue INVOKESTATIC = byteValue(0xb8);
	private final ConstantPoolIndex methodReference;

	private Invokestatic(ConstantPoolIndex methodReference) {
		this.methodReference = methodReference;
	}

	public static Invokestatic invokestatic(ConstantPoolIndex methodReference) {
		return new Invokestatic(methodReference);
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		INVOKESTATIC.writeTo(dataOutput);
		methodReference.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return INVOKESTATIC.sizeInBytes() + methodReference.sizeInBytes();
	}
}
