package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.common.WithAssertions;
import io.github.theangrydev.op.generation.jvm.ByteValue;
import org.junit.Test;

import static io.github.theangrydev.op.generation.ByteWriter.bytes;
import static io.github.theangrydev.op.generation.ByteWriter.bytesWrittenBy;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Bipush.bipush;

public class BipushTest implements WithAssertions {

	private static final ByteValue BYTE_TO_PUSH = ByteValue.byteValue(99);

	@Test
	public void writesOpcodeAndByteArgument() throws Exception {
		assertThat(bytesWrittenBy(bipush(BYTE_TO_PUSH))).containsExactly(bytes((byte) 0x10, bytesWrittenBy(BYTE_TO_PUSH)));
	}

	@Test
	public void isTwoBytesLong() throws Exception {
		assertThat(bipush(BYTE_TO_PUSH).sizeInBytes()).isEqualTo(2);
	}
}
