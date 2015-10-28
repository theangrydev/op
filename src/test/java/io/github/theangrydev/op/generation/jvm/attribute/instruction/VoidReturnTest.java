package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.common.WithAssertions;
import org.junit.Test;

import static io.github.theangrydev.op.generation.ByteWriter.bytesWrittenBy;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.VoidReturn.voidreturn;

public class VoidReturnTest implements WithAssertions {

	@Test
	public void writesOpcode() throws Exception {
		assertThat(bytesWrittenBy(voidreturn())).containsExactly((byte) 0xb1);
	}

	@Test
	public void isOneByteLong() throws Exception {
		assertThat(voidreturn().sizeInBytes()).isEqualTo(1);
	}
}
