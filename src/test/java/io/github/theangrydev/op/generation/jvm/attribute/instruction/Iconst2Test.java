package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.common.WithAssertions;
import org.junit.Test;

import static io.github.theangrydev.op.generation.ByteWriter.bytesWrittenBy;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Iconst2.iconst2;

public class Iconst2Test implements WithAssertions {

	@Test
	public void writesOpcode() throws Exception {
		assertThat(bytesWrittenBy(iconst2())).containsExactly((byte) 0x05);
	}

	@Test
	public void isOneByteLong() throws Exception {
		assertThat(iconst2().sizeInBytes()).isEqualTo(1);
	}
}
