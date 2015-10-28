package io.github.theangrydev.op.generation.jvm.attribute;

import io.github.theangrydev.op.generation.jvm.constant.ConstantPoolIndex;

import java.io.DataOutput;
import java.io.IOException;

public class ConstantValueAttributeInfo implements AttributeInfo {
	private ConstantPoolIndex constantValueIndex;

	private ConstantValueAttributeInfo(ConstantPoolIndex constantValueIndex) {
		this.constantValueIndex = constantValueIndex;
	}

	public static ConstantValueAttributeInfo constantValueAttributeInfo(ConstantPoolIndex constantValueIndex) {
		return new ConstantValueAttributeInfo(constantValueIndex);
	}

	@Override
	public int sizeInBytes() {
		return constantValueIndex.sizeInBytes();
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		constantValueIndex.writeTo(dataOutput);
	}
}
