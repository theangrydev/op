package io.github.theangrydev.op.generation.jvm;

import com.googlecode.yatspec.junit.Row;
import com.googlecode.yatspec.junit.Table;
import com.googlecode.yatspec.junit.TableRunner;
import io.github.theangrydev.op.common.WithAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.github.theangrydev.op.generation.ByteWriter.bytesWrittenBy;
import static io.github.theangrydev.op.generation.jvm.IntValue.intValue;

@RunWith(TableRunner.class)
public class IntValueTest implements WithAssertions {

	@Test
	public void rejectsNegativeValues() {
		assertThatThrownBy(() -> intValue(-1))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("negative")
			.hasMessageContaining("-1");
	}

	@Table({
		@Row("0"),
		@Row("999999999")
	})
	@Test
	public void writesBytesInBigEndian(String valueString) throws Exception {
		int value = Integer.parseInt(valueString);
		assertThat(bytesWrittenBy(intValue(value))).containsExactly((byte) (value >> 24), (byte) (value >> 16), (byte) (value >> 8), (byte) value);
	}

	@Test
	public void isFourBytesLong() throws Exception {
		assertThat(intValue(999999999).sizeInBytes()).isEqualTo(4);
	}
}
