package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.common.WithAssertions;
import org.junit.Test;

import static io.github.theangrydev.op.generation.ByteWriter.bytesWrittenBy;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Ireturn.ireturn;

public class IreturnTest implements WithAssertions {

	@Test
	public void writesOpcode() throws Exception {
		assertThat(bytesWrittenBy(ireturn())).containsExactly((byte) 0xac);
	}

	@Test
	public void isOneByteLong() throws Exception {
		assertThat(ireturn().sizeInBytes()).isEqualTo(1);
	}
}
