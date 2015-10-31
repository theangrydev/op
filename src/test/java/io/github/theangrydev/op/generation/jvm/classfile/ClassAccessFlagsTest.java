package io.github.theangrydev.op.generation.jvm.classfile;

import io.github.theangrydev.op.common.WithAssertions;
import org.junit.Test;

import java.io.IOException;

import static io.github.theangrydev.op.generation.ByteWriter.bytesWrittenBy;
import static io.github.theangrydev.op.generation.jvm.ShortValue.shortValue;
import static io.github.theangrydev.op.generation.jvm.classfile.ClassAccessFlag.PUBLIC;
import static io.github.theangrydev.op.generation.jvm.classfile.ClassAccessFlag.TREAT_SUPER_METHODS_SPECIALLY;
import static io.github.theangrydev.op.generation.jvm.classfile.ClassAccessFlags.classAccessFlags;

public class ClassAccessFlagsTest implements WithAssertions {

	@Test
	public void writesSetFlagsAsAnInteger() throws IOException {
		assertThat(bytesWrittenBy(classAccessFlags(PUBLIC, TREAT_SUPER_METHODS_SPECIALLY))).containsExactly(bytesWrittenBy(shortValue(PUBLIC.value() | TREAT_SUPER_METHODS_SPECIALLY.value())));
	}
}
