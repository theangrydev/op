package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.common.WithAssertions;
import org.junit.Test;

import static io.github.theangrydev.op.generation.ByteWriter.bytesWrittenBy;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Iload1.iload1;

public class Iload1Test implements WithAssertions {

	@Test
	public void writesOpcode() throws Exception {
		assertThat(bytesWrittenBy(iload1())).containsExactly((byte) 0x1b);
	}

	@Test
	public void isOneByteLong() throws Exception {
		assertThat(iload1().sizeInBytes()).isEqualTo(1);
	}
}
