package io.github.theangrydev.op.generation.jvm.constant;

import io.github.theangrydev.op.common.WithReflectiveEqualsAndHashCode;

import java.io.DataOutput;
import java.io.IOException;

public class Utf8InfoConstant extends WithReflectiveEqualsAndHashCode implements ConstantInfo {

	private final String value;

	private Utf8InfoConstant(String value) {
		this.value = value;
	}

	public static Utf8InfoConstant utf8InfoConstant(String value) {
		return new Utf8InfoConstant(value);
	}

	@Override
	public byte tag() {
		return 1;
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		dataOutput.writeUTF(value);
	}
}
