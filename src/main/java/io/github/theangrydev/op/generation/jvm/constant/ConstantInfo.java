package io.github.theangrydev.op.generation.jvm.constant;

import io.github.theangrydev.op.generation.jvm.ClassFileWriter;

public interface ConstantInfo extends ClassFileWriter {
	byte tag();
}
