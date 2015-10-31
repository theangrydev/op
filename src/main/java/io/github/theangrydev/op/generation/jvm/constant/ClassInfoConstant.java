package io.github.theangrydev.op.generation.jvm.constant;

import java.io.DataOutput;
import java.io.IOException;

public class ClassInfoConstant implements ConstantInfo {

	private final ConstantPoolIndex<Utf8InfoConstant> nameIndex;

	private ClassInfoConstant(ConstantPoolIndex<Utf8InfoConstant> nameIndex) {
		this.nameIndex = nameIndex;
	}

	public static ClassInfoConstant classInfo(ConstantPoolIndex<Utf8InfoConstant> constantPoolIndex) {
		return new ClassInfoConstant(constantPoolIndex);
	}

	@Override
	public byte tag() {
		return 7;
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		nameIndex.writeTo(dataOutput);
	}
}
