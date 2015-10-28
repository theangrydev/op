package io.github.theangrydev.op.generation.jvm.constant;

import java.io.DataOutput;
import java.io.IOException;

public class IntegerConstant implements ConstantInfo {

	private final int value;

	private IntegerConstant(int value) {
		this.value = value;
	}

	public static IntegerConstant integerConstant(int value) {
		return new IntegerConstant(value);
	}

	@Override
	public byte tag() {
		return 3;
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		dataOutput.writeInt(value);
	}
}
