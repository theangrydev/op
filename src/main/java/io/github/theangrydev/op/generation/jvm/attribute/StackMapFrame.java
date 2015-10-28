package io.github.theangrydev.op.generation.jvm.attribute;

import io.github.theangrydev.op.generation.jvm.ClassFileWriter;
import io.github.theangrydev.op.generation.jvm.WithSizeInBytes;

import java.io.DataOutput;
import java.io.IOException;

public class StackMapFrame implements ClassFileWriter, WithSizeInBytes {
	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {

	}

	@Override
	public int sizeInBytes() {
		return 0;
	}
}
