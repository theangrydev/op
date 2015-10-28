package io.github.theangrydev.op.generation.jvm.field;

import io.github.theangrydev.op.generation.jvm.ClassFileWriter;
import io.github.theangrydev.op.generation.jvm.ShortValue;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.ShortValue.shortValue;

public class Fields implements ClassFileWriter {

	private final ShortValue numberOfFields = shortValue(0);

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		numberOfFields.writeTo(dataOutput);
	}
}
