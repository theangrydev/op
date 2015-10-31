package io.github.theangrydev.op.generation.jvm.constant;

import io.github.theangrydev.op.common.WithReflectiveEqualsAndHashCode;
import io.github.theangrydev.op.generation.jvm.ByteValue;
import io.github.theangrydev.op.generation.jvm.ClassFileWriter;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.ByteValue.byteValue;

public class Constant extends WithReflectiveEqualsAndHashCode implements ClassFileWriter {

	private final ByteValue tag;
	private final ConstantInfo constantInfo;

	private Constant(ByteValue tag, ConstantInfo constantInfo) {
		this.tag = tag;
		this.constantInfo = constantInfo;
	}

	public static Constant constant(ConstantInfo constantInfo) {
		ByteValue tag = byteValue(constantInfo.tag());
		return new Constant(tag, constantInfo);
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		tag.writeTo(dataOutput);
		constantInfo.writeTo(dataOutput);
	}
}
