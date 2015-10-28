package io.github.theangrydev.op.generation.jvm.constant;

import java.io.DataOutput;
import java.io.IOException;

public class FieldReferenceInfoConstant implements ConstantInfo {

	private final ConstantPoolIndex classIndex;
	private final ConstantPoolIndex nameAndTypeIndex;

	private FieldReferenceInfoConstant(ConstantPoolIndex classIndex, ConstantPoolIndex nameAndTypeIndex) {
		this.classIndex = classIndex;
		this.nameAndTypeIndex = nameAndTypeIndex;
	}

	public static FieldReferenceInfoConstant fieldReferenceInfoConstant(ConstantPoolIndex classIndex, ConstantPoolIndex nameAndTypeIndex) {
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
