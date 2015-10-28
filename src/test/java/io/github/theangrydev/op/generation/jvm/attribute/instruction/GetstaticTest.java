package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.common.WithAssertions;
import io.github.theangrydev.op.generation.jvm.constant.ConstantPoolIndex;
import org.junit.Test;

import static io.github.theangrydev.op.generation.ByteWriter.bytes;
import static io.github.theangrydev.op.generation.ByteWriter.bytesWrittenBy;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Getstatic.getstatic;
import static io.github.theangrydev.op.generation.jvm.constant.ConstantPoolIndex.constantPoolIndex;

public class GetstaticTest implements WithAssertions {

	private static final ConstantPoolIndex FIELD_REFERENCE = constantPoolIndex(9999);

	@Test
	public void writesOpcodeAndIndexBytes() throws Exception {
		assertThat(bytesWrittenBy(getstatic(FIELD_REFERENCE))).containsExactly(bytes((byte) 0xb2, bytesWrittenBy(FIELD_REFERENCE)));
	}

	@Test
	public void isThreeBytesLong() throws Exception {
		assertThat(getstatic(FIELD_REFERENCE).sizeInBytes()).isEqualTo(3);
	}
}
