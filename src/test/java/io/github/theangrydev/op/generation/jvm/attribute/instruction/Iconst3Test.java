package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.common.WithAssertions;
import org.junit.Test;

import static io.github.theangrydev.op.generation.ByteWriter.bytesWrittenBy;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Iconst3.iconst3;

public class Iconst3Test implements WithAssertions {

	@Test
	public void writesOpcode() throws Exception {
		assertThat(bytesWrittenBy(iconst3())).containsExactly((byte) 0x06);
	}

	@Test
	public void isOneByteLong() throws Exception {
		assertThat(iconst3().sizeInBytes()).isEqualTo(1);
	}
}
