package io.github.theangrydev.op.generation.jvm;

import com.google.common.base.Preconditions;

import java.io.DataOutput;
import java.io.IOException;

public class IntValue implements ClassFileWriter, WithSizeInBytes{

	private final int value;

	private IntValue(int value) {
		this.value = value;
	}

	public static IntValue intValue(int value) {
		Preconditions.checkArgument(value >= 0, "%s may not be negative but was %s", name(), value);
		return new IntValue(value);
	}

	private static String name() {
		return IntValue.class.getSimpleName();
	}

	public int value() {
		return value;
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		dataOutput.writeInt(value);
	}

	@Override
	public int sizeInBytes() {
		return 4;
	}
}
