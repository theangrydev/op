package io.github.theangrydev.op.generation.jvm;

import com.googlecode.yatspec.junit.Row;
import com.googlecode.yatspec.junit.Table;
import com.googlecode.yatspec.junit.TableRunner;
import io.github.theangrydev.op.common.WithAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.github.theangrydev.op.generation.ByteWriter.bytesWrittenBy;
import static io.github.theangrydev.op.generation.jvm.ByteValue.byteValue;
import static java.lang.Integer.parseInt;

@RunWith(TableRunner.class)
public class ByteValueTest implements WithAssertions {

	@Test
	public void rejectsNegativeValues() {
		assertThatThrownBy(() -> byteValue(-1))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("negative")
			.hasMessageContaining("-1");
	}

	@Test
	public void rejectsValuesBiggerThan255() {
		assertThatThrownBy(() -> byteValue(256))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("255")
			.hasMessageContaining("256");
	}

	@Table({
		@Row("0"),
		@Row("255"),
		@Row("100")
	})
	@Test
	public void writesByte(String valueString) throws Exception {
		int value = parseInt(valueString);
		assertThat(bytesWrittenBy(byteValue(value))).containsExactly((byte) value);
	}

	@Test
	public void isOneByteLong() throws Exception {
		assertThat(byteValue(200).sizeInBytes()).isEqualTo(1);
	}
}
