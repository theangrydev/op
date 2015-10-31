package io.github.theangrydev.op.generation.jvm;

import com.googlecode.yatspec.junit.Row;
import com.googlecode.yatspec.junit.Table;
import com.googlecode.yatspec.junit.TableRunner;
import io.github.theangrydev.op.common.WithAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.github.theangrydev.op.generation.ByteWriter.bytesWrittenBy;
import static io.github.theangrydev.op.generation.jvm.ShortValue.shortValue;

@RunWith(TableRunner.class)
public class ShortValueTest implements WithAssertions {

	@Test
	public void rejectsNegativeValues() {
		assertThatThrownBy(() -> shortValue(-1))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("negative")
			.hasMessageContaining("-1");
	}

	@Test
	public void rejectsValuesBiggerThan65536() {
		assertThatThrownBy(() -> shortValue(65536))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("65536")
			.hasMessageContaining("65535");
	}

	@Table({
		@Row("0"),
		@Row("65535"),
		@Row("10000")
	})
	@Test
	public void writesBytesInBigEndian(String valueString) throws Exception {
		int value = Integer.parseInt(valueString);
		assertThat(bytesWrittenBy(shortValue(value))).containsExactly((byte) (value >> 8), (byte) value);
	}

	@Test
	public void isTwoBytesLong() throws Exception {
		assertThat(shortValue(9999).sizeInBytes()).isEqualTo(2);
	}
}
