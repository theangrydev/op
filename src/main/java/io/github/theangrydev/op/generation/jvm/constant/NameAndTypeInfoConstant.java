package io.github.theangrydev.op.generation.jvm.constant;

import java.io.DataOutput;
import java.io.IOException;

public class NameAndTypeInfoConstant implements ConstantInfo {

	private final ConstantPoolIndex nameIndex;
	private final ConstantPoolIndex descriptorIndex;

	private NameAndTypeInfoConstant(ConstantPoolIndex nameIndex, ConstantPoolIndex descriptorIndex) {
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
	}

	public static NameAndTypeInfoConstant nameAndTypeInfoConstant(ConstantPoolIndex nameIndex, ConstantPoolIndex descriptorIndex) {
		return new NameAndTypeInfoConstant(nameIndex, descriptorIndex);
	}

	@Override
	public byte tag() {
		return 12;
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		nameIndex.writeTo(dataOutput);
		descriptorIndex.writeTo(dataOutput);
	}
}
