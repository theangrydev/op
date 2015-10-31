package io.github.theangrydev.op.generation.jvm.method;

import io.github.theangrydev.op.generation.jvm.ClassFileWriter;
import io.github.theangrydev.op.generation.jvm.ShortValue;

import java.io.DataOutput;
import java.io.IOException;
import java.util.Set;

import static io.github.theangrydev.op.generation.jvm.Flag.combine;
import static io.github.theangrydev.op.generation.jvm.ShortValue.shortValue;

public class MethodAccessFlags implements ClassFileWriter {

	private final ShortValue flags;

	private MethodAccessFlags(ShortValue flags) {
		this.flags = flags;
	}

	public static MethodAccessFlags methodAccessFlags(Set<MethodAccessFlag> flags) {
		return new MethodAccessFlags(shortValue(combine(flags)));
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		flags.writeTo(dataOutput);
	}
}
