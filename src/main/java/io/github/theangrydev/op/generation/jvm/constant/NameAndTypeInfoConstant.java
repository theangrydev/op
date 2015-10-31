package io.github.theangrydev.op.generation.jvm.constant;

import io.github.theangrydev.op.common.WithReflectiveEqualsAndHashCode;

import java.io.DataOutput;
import java.io.IOException;

public class NameAndTypeInfoConstant extends WithReflectiveEqualsAndHashCode implements ConstantInfo {

	private final ConstantPoolIndex<Utf8InfoConstant> nameIndex;
	private final ConstantPoolIndex<Utf8InfoConstant> descriptorIndex;

	private NameAndTypeInfoConstant(ConstantPoolIndex<Utf8InfoConstant> nameIndex, ConstantPoolIndex<Utf8InfoConstant> descriptorIndex) {
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
	}

	public static NameAndTypeInfoConstant nameAndTypeInfoConstant(ConstantPoolIndex<Utf8InfoConstant> nameIndex, ConstantPoolIndex<Utf8InfoConstant> descriptorIndex) {
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
