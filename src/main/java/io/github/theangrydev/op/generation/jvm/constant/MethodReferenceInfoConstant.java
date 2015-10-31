package io.github.theangrydev.op.generation.jvm.constant;

import java.io.DataOutput;
import java.io.IOException;

public class MethodReferenceInfoConstant implements ConstantInfo {

	private final ConstantPoolIndex<ClassInfoConstant> classIndex;
	private final ConstantPoolIndex<NameAndTypeInfoConstant> nameAndTypeIndex;

	private MethodReferenceInfoConstant(ConstantPoolIndex<ClassInfoConstant> classIndex, ConstantPoolIndex<NameAndTypeInfoConstant> nameAndTypeIndex) {
		this.classIndex = classIndex;
		this.nameAndTypeIndex = nameAndTypeIndex;
	}

	public static MethodReferenceInfoConstant methodReferenceInfoConstant(ConstantPoolIndex<ClassInfoConstant> classIndex, ConstantPoolIndex<NameAndTypeInfoConstant> nameAndTypeIndex) {
		return new MethodReferenceInfoConstant(classIndex, nameAndTypeIndex);
	}

	@Override
	public byte tag() {
		return 10;
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		classIndex.writeTo(dataOutput);
		nameAndTypeIndex.writeTo(dataOutput);
	}
}
