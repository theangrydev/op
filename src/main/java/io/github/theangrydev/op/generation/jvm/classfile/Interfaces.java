package io.github.theangrydev.op.generation.jvm.classfile;

import io.github.theangrydev.op.generation.jvm.ClassFileWriter;
import io.github.theangrydev.op.generation.jvm.ShortValue;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.ShortValue.shortValue;

public class Interfaces implements ClassFileWriter {
	private final ShortValue numberOfInterfaces = shortValue(0);

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		numberOfInterfaces.writeTo(dataOutput);
	}
}
