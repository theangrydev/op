package io.github.theangrydev.op.generation.jvm.method;

import io.github.theangrydev.op.generation.jvm.ClassFileWriter;
import io.github.theangrydev.op.generation.jvm.attribute.Attributes;
import io.github.theangrydev.op.generation.jvm.constant.ConstantPoolIndex;

import java.io.DataOutput;
import java.io.IOException;

public class MethodInfo implements ClassFileWriter {

	private final MethodAccessFlags methodAccessFlags;
	private final ConstantPoolIndex nameIndex;
	private final ConstantPoolIndex descriptorIndex;
	private final Attributes attributes;

	private MethodInfo(MethodAccessFlags methodAccessFlags, ConstantPoolIndex nameIndex, ConstantPoolIndex descriptorIndex, Attributes attributes) {
		this.methodAccessFlags = methodAccessFlags;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.attributes = attributes;
	}

	public static MethodInfo methodInfo(ConstantPoolIndex nameIndex, ConstantPoolIndex descriptorIndex, Attributes attributes, MethodAccessFlags methodAccessFlags) {
		return new MethodInfo(methodAccessFlags, nameIndex, descriptorIndex, attributes);
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		methodAccessFlags.writeTo(dataOutput);
		nameIndex.writeTo(dataOutput);
		descriptorIndex.writeTo(dataOutput);
		attributes.writeTo(dataOutput);
	}
}
