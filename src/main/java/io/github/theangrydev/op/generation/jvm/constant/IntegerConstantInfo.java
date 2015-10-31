package io.github.theangrydev.op.generation.jvm.constant;

import io.github.theangrydev.op.generation.jvm.IntValue;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.IntValue.intValue;

public class IntegerConstantInfo implements ConstantInfo {

	private final IntValue value;

	private IntegerConstantInfo(IntValue value) {
		this.value = value;
	}

	public static IntegerConstantInfo integerConstant(int value) {
		return new IntegerConstantInfo(intValue(value));
	}

	@Override
	public byte tag() {
		return 3;
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		value.writeTo(dataOutput);
	}
}
