package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.generation.jvm.ByteValue;
import io.github.theangrydev.op.generation.jvm.constant.ConstantPoolIndex;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.ByteValue.byteValue;

public class Invokevirtual implements Instruction {

	private static final ByteValue INVOKEVIRTUAL = byteValue(0xb6);
	private final ConstantPoolIndex methodReference;

	private Invokevirtual(ConstantPoolIndex methodReference) {
		this.methodReference = methodReference;
	}

	public static Invokevirtual invokevirtual(ConstantPoolIndex methodReference) {
		return new Invokevirtual(methodReference);
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		INVOKEVIRTUAL.writeTo(dataOutput);
		methodReference.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return INVOKEVIRTUAL.sizeInBytes() + methodReference.sizeInBytes();
	}
}
