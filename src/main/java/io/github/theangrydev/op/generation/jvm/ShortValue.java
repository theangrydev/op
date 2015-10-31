package io.github.theangrydev.op.generation.jvm;

import com.google.common.base.Preconditions;

import java.io.DataOutput;
import java.io.IOException;

public class ShortValue implements ClassFileWriter, WithSizeInBytes {

	private static final int MAX_SHORT_VALUE = 65535;
	private final int value;

	private ShortValue(int value) {
		this.value = value;
	}

	public static ShortValue shortValue(int value) {
		Preconditions.checkArgument(value >= 0, "%s may not be negative but was %s", name(), value);
		Preconditions.checkArgument(value <= MAX_SHORT_VALUE, "%s can be at most %s but was %s", name(), MAX_SHORT_VALUE, value);
		return new ShortValue(value);
	}

	private static String name() {
		return ShortValue.class.getSimpleName();
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		dataOutput.writeShort(value);
	}

	@Override
	public int sizeInBytes() {
		return 2;
	}

	public int value() {
		return value;
	}
}
