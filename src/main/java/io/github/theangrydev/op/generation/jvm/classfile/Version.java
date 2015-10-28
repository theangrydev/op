package io.github.theangrydev.op.generation.jvm.classfile;

import io.github.theangrydev.op.generation.jvm.ClassFileWriter;
import io.github.theangrydev.op.generation.jvm.ShortValue;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.ShortValue.shortValue;

public class Version implements ClassFileWriter {

	private final ShortValue minorVersion;
	private final ShortValue majorVersion;

	private Version(ShortValue minorVersion, ShortValue majorVersion) {
		this.minorVersion = minorVersion;
		this.majorVersion = majorVersion;
	}

	public static Version java8() {
		return new Version(shortValue(0), shortValue(52));
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		minorVersion.writeTo(dataOutput);
		majorVersion.writeTo(dataOutput);
	}
}
