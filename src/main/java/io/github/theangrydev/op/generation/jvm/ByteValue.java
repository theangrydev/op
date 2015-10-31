package io.github.theangrydev.op.generation.jvm;

import com.google.common.base.Preconditions;
import io.github.theangrydev.op.common.WithReflectiveEqualsAndHashCode;

import java.io.DataOutput;
import java.io.IOException;

public class ByteValue extends WithReflectiveEqualsAndHashCode implements ClassFileWriter, WithSizeInBytes {

	private static final int MAX_BYTE_VALUE = 255;
	private final int value;

	private ByteValue(int value) {
		this.value = value;
	}

	public static ByteValue byteValue(int value) {
		Preconditions.checkArgument(value >= 0, "%s may not be negative but was %s", name(), value);
		Preconditions.checkArgument(value <= MAX_BYTE_VALUE, "%s can be at most %s but was %s", name(), MAX_BYTE_VALUE, value);
		return new ByteValue(value);
	}

	private static String name() {
		return ByteValue.class.getSimpleName();
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		dataOutput.write(value);
	}

	@Override
	public int sizeInBytes() {
		return 1;
	}
}
