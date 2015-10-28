package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.common.WithAssertions;
import org.junit.Test;

import static io.github.theangrydev.op.generation.ByteWriter.bytesWrittenBy;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Iadd.iadd;

public class IaddTest implements WithAssertions {

	@Test
	public void writesOpcode() throws Exception {
		assertThat(bytesWrittenBy(iadd())).containsExactly((byte) 0x60);
	}

	@Test
	public void isOneByteLong() throws Exception {
		assertThat(iadd().sizeInBytes()).isEqualTo(1);
	}
}
