package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.common.WithAssertions;
import org.junit.Test;

import static io.github.theangrydev.op.generation.ByteWriter.bytesWrittenBy;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Iload2.iload2;

public class Iload2Test implements WithAssertions {

	@Test
	public void writesOpcode() throws Exception {
		assertThat(bytesWrittenBy(iload2())).containsExactly((byte) 0x1c);
	}

	@Test
	public void isOneByteLong() throws Exception {
		assertThat(iload2().sizeInBytes()).isEqualTo(1);
	}
}
