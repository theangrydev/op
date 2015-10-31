package io.github.theangrydev.op.generation.jvm.constant;

import com.googlecode.yatspec.junit.Row;
import com.googlecode.yatspec.junit.Table;
import com.googlecode.yatspec.junit.TableRunner;
import io.github.theangrydev.op.common.WithAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static io.github.theangrydev.op.generation.ByteWriter.bytes;
import static io.github.theangrydev.op.generation.ByteWriter.bytesWrittenBy;
import static io.github.theangrydev.op.generation.jvm.ShortValue.shortValue;
import static io.github.theangrydev.op.generation.jvm.constant.Utf8InfoConstant.utf8InfoConstant;

@RunWith(TableRunner.class)
public class Utf8InfoConstantTest implements WithAssertions {

	@Test
	public void tagMatchesTheJvmSpecification() {
		assertThat(utf8InfoConstant("").tag()).isEqualTo((byte) 1);
	}

	@Table({
		@Row(""),
		@Row("non empty string")
	})
	@Test
	public void writesLengthBytesThenUtf8Bytes(String string) throws Exception {
		assertThat(bytesWrittenBy(utf8InfoConstant(string))).containsExactly(bytes(lengthBytes(string), string.getBytes()));
	}

	private byte[] lengthBytes(String string) throws IOException {
		return bytesWrittenBy(shortValue(string.length()));
	}
}
