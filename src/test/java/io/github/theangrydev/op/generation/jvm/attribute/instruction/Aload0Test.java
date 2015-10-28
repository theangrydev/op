package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.common.WithAssertions;
import org.junit.Test;

import static io.github.theangrydev.op.generation.ByteWriter.bytesWrittenBy;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Aload0.aload0;

public class Aload0Test implements WithAssertions {

	@Test
	public void writesOpcode() throws Exception {
		assertThat(bytesWrittenBy(aload0())).containsExactly((byte) 0x2a);
	}

	@Test
	public void isOneByteLong() throws Exception {
		assertThat(aload0().sizeInBytes()).isEqualTo(1);
	}
}
