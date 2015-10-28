package io.github.theangrydev.op.generation.jvm.constant;

import io.github.theangrydev.op.generation.jvm.ClassFileWriter;
import io.github.theangrydev.op.generation.jvm.ShortValue;
import io.github.theangrydev.op.generation.jvm.WithSizeInBytes;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.ShortValue.shortValue;

public class ConstantPoolIndex implements ClassFileWriter, WithSizeInBytes {

	private final ShortValue index;

	private ConstantPoolIndex(ShortValue index) {
		this.index = index;
	}

	public static ConstantPoolIndex constantPoolIndex(int index) {
		return new ConstantPoolIndex(shortValue(index));
	}

	public int index() {
		return index.value();
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		index.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return 2;
	}
}
