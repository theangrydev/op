/*
 * Copyright 2015-2020 Liam Williams <liam.williams@zoho.com>.
 *
 * This file is part of op.
 *
 * op is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * op is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with op.  If not, see <http://www.gnu.org/licenses/>.
 */
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
