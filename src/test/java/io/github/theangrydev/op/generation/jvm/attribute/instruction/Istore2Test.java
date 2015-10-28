package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.common.WithAssertions;
import org.junit.Test;

import static io.github.theangrydev.op.generation.ByteWriter.bytesWrittenBy;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Istore2.istore2;

public class Istore2Test implements WithAssertions {

	@Test
	public void writesOpcode() throws Exception {
		assertThat(bytesWrittenBy(istore2())).containsExactly((byte) 0x3d);
	}

	@Test
	public void isOneByteLong() throws Exception {
		assertThat(istore2().sizeInBytes()).isEqualTo(1);
	}
}
