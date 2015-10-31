package io.github.theangrydev.op.generation.jvm.constant;

import io.github.theangrydev.op.common.WithAssertions;
import org.junit.Test;

import static io.github.theangrydev.op.generation.ByteWriter.bytesWrittenBy;
import static io.github.theangrydev.op.generation.jvm.IntValue.intValue;
import static io.github.theangrydev.op.generation.jvm.constant.IntegerConstantInfo.integerConstant;

public class IntegerConstantInfoTest implements WithAssertions {

	@Test
	public void tagMatchesTheJvmSpecification() {
		assertThat(integerConstant(1).tag()).isEqualTo((byte) 3);
	}

	@Test
	public void writesLengthBytesThenUtf8Bytes() throws Exception {
		assertThat(bytesWrittenBy(integerConstant(1))).containsExactly(bytesWrittenBy(intValue(1)));
	}
}
