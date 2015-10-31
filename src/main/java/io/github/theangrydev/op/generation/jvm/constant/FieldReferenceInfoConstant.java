package io.github.theangrydev.op.generation.jvm.constant;

import io.github.theangrydev.op.common.WithReflectiveEqualsAndHashCode;

import java.io.DataOutput;
import java.io.IOException;

public class FieldReferenceInfoConstant extends WithReflectiveEqualsAndHashCode implements ConstantInfo {

	private final ConstantPoolIndex<ClassInfoConstant> classIndex;
	private final ConstantPoolIndex<NameAndTypeInfoConstant> nameAndTypeIndex;

	private FieldReferenceInfoConstant(ConstantPoolIndex<ClassInfoConstant> classIndex, ConstantPoolIndex<NameAndTypeInfoConstant> nameAndTypeIndex) {
		this.classIndex = classIndex;
		this.nameAndTypeIndex = nameAndTypeIndex;
	}

	public static FieldReferenceInfoConstant fieldReferenceInfoConstant(ConstantPoolIndex<ClassInfoConstant> classIndex, ConstantPoolIndex<NameAndTypeInfoConstant> nameAndTypeIndex) {
		return new FieldReferenceInfoConstant(classIndex, nameAndTypeIndex);
	}

	@Override
	public byte tag() {
		return 9;
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		classIndex.writeTo(dataOutput);
		nameAndTypeIndex.writeTo(dataOutput);
	}
}
