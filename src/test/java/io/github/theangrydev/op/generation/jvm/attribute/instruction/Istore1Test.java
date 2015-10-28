package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.common.WithAssertions;
import org.junit.Test;

import static io.github.theangrydev.op.generation.ByteWriter.bytesWrittenBy;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Istore1.istore1;

public class Istore1Test implements WithAssertions {

	@Test
	public void writesOpcode() throws Exception {
		assertThat(bytesWrittenBy(istore1())).containsExactly((byte) 0x3c);
	}

	@Test
	public void isOneByteLong() throws Exception {
		assertThat(istore1().sizeInBytes()).isEqualTo(1);
	}
}
