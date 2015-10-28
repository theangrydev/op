package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.common.WithAssertions;
import org.junit.Test;

import static io.github.theangrydev.op.generation.ByteWriter.bytesWrittenBy;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Iload0.iload0;

public class Iload0Test implements WithAssertions {

	@Test
	public void writesOpcode() throws Exception {
		assertThat(bytesWrittenBy(iload0())).containsExactly((byte) 0x1a);
	}

	@Test
	public void isOneByteLong() throws Exception {
		assertThat(iload0().sizeInBytes()).isEqualTo(1);
	}
}
