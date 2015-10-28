package io.github.theangrydev.op.generation.jvm.attribute;

import io.github.theangrydev.op.generation.jvm.ClassFileWriter;
import io.github.theangrydev.op.generation.jvm.IntValue;
import io.github.theangrydev.op.generation.jvm.WithSizeInBytes;
import io.github.theangrydev.op.generation.jvm.constant.ConstantPoolIndex;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.IntValue.intValue;

public class Attribute implements ClassFileWriter, WithSizeInBytes {
	private final ConstantPoolIndex attributeNameIndex;
	private final AttributeInfo attributeInfo;
	private final IntValue attributeLengthInBytes;

	private Attribute(ConstantPoolIndex attributeNameIndex, AttributeInfo attributeInfo, IntValue attributeLengthInBytes) {
		this.attributeNameIndex = attributeNameIndex;
		this.attributeInfo = attributeInfo;
		this.attributeLengthInBytes = attributeLengthInBytes;
	}

	public static Attribute attribute(ConstantPoolIndex attributeNameIndex, AttributeInfo attributeInfo) {
		IntValue attributeLengthInBytes = intValue(attributeInfo.sizeInBytes());
		return new Attribute(attributeNameIndex, attributeInfo, attributeLengthInBytes);
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		attributeNameIndex.writeTo(dataOutput);
		attributeLengthInBytes.writeTo(dataOutput);
		attributeInfo.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return attributeLengthInBytes.sizeInBytes() + attributeNameIndex.sizeInBytes() + attributeInfo.sizeInBytes();
	}
}
