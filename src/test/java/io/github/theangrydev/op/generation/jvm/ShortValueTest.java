/*
 * Copyright 2015 Liam Williams <liam.williams@zoho.com>.
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
