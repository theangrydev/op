package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.common.WithAssertions;
import io.github.theangrydev.op.generation.jvm.constant.ConstantPoolIndex;
import org.junit.Test;

import static io.github.theangrydev.op.generation.ByteWriter.bytes;
import static io.github.theangrydev.op.generation.ByteWriter.bytesWrittenBy;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Invokestatic.invokestatic;
import static io.github.theangrydev.op.generation.jvm.constant.ConstantPoolIndex.constantPoolIndex;

public class InvokestaticTest implements WithAssertions {

	private static final ConstantPoolIndex METHOD_REFERENCE = constantPoolIndex(9999);

	@Test
	public void writesOpcodeAndIndexBytes() throws Exception {
		assertThat(bytesWrittenBy(invokestatic(METHOD_REFERENCE))).containsExactly(bytes((byte) 0xb8, bytesWrittenBy(METHOD_REFERENCE)));
	}

	@Test
	public void isThreeBytesLong() throws Exception {
		assertThat(invokestatic(METHOD_REFERENCE).sizeInBytes()).isEqualTo(3);
	}
}
