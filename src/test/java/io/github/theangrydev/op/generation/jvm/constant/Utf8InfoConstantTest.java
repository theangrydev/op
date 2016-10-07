/*
 * Copyright 2015-2016 Liam Williams <liam.williams@zoho.com>.
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
