package io.github.theangrydev.op.generation.jvm.attribute;

import io.github.theangrydev.op.generation.jvm.ClassFileWriter;
import io.github.theangrydev.op.generation.jvm.ShortValue;
import io.github.theangrydev.op.generation.jvm.WithSizeInBytes;

import java.io.DataOutput;
import java.io.IOException;

public class ExceptionTable implements ClassFileWriter, WithSizeInBytes {

	private ShortValue length = ShortValue.shortValue(0);

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		length.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return length.sizeInBytes();
	}

	public static ExceptionTable exceptionTable() {
		return new ExceptionTable();
	}
}
