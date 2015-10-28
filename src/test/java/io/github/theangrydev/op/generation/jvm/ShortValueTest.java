package io.github.theangrydev.op.generation.jvm;

import io.github.theangrydev.op.common.WithAssertions;
import org.junit.Test;

import static io.github.theangrydev.op.generation.ByteWriter.bytesWrittenBy;
import static io.github.theangrydev.op.generation.jvm.ShortValue.shortValue;

public class ShortValueTest implements WithAssertions {

	private static final int VALUE = 9999;

	@Test
	public void writesOpcodeAndByteArgument() throws Exception {
		assertThat(bytesWrittenBy(shortValue(VALUE))).containsExactly((byte) (VALUE >> 8), (byte) VALUE);
	}

	@Test
	public void isTwoBytesLong() throws Exception {
		assertThat(shortValue(VALUE).sizeInBytes()).isEqualTo(2);
	}
}
