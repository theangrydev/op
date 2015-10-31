package io.github.theangrydev.op.generation.jvm.classfile;

import io.github.theangrydev.op.generation.jvm.ClassFileWriter;
import io.github.theangrydev.op.generation.jvm.ShortValue;

import java.io.DataOutput;
import java.io.IOException;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static io.github.theangrydev.op.generation.jvm.Flag.combine;
import static io.github.theangrydev.op.generation.jvm.ShortValue.shortValue;

public class ClassAccessFlags implements ClassFileWriter {

	private final ShortValue flags;

	public ClassAccessFlags(ShortValue flags) {
		this.flags = flags;
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		flags.writeTo(dataOutput);
	}

	public static ClassAccessFlags classAccessFlags(Set<ClassAccessFlag> flags) {
		return new ClassAccessFlags(shortValue(combine(flags)));
	}

	public static ClassAccessFlags classAccessFlags(ClassAccessFlag... flags) {
		return classAccessFlags(newHashSet(flags));
	}
}
