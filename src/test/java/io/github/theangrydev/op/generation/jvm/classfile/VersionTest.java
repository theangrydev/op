package io.github.theangrydev.op.generation.jvm.classfile;

import io.github.theangrydev.op.common.WithAssertions;
import org.junit.Test;

import java.io.IOException;

import static io.github.theangrydev.op.generation.ByteWriter.bytesWrittenBy;
import static io.github.theangrydev.op.generation.jvm.ShortValue.shortValue;

public class VersionTest implements WithAssertions {

	@Test
	public void writesJava8Version() throws IOException {
		assertThat(bytesWrittenBy(Version.java8())).containsExactly(bytesWrittenBy(shortValue(0), shortValue(52)));
	}
}
