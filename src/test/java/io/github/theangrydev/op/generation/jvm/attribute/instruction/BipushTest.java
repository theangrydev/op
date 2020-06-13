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
package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.common.WithAssertions;
import io.github.theangrydev.op.generation.jvm.ByteValue;
import org.junit.Test;

import static io.github.theangrydev.op.generation.ByteWriter.bytes;
import static io.github.theangrydev.op.generation.ByteWriter.bytesWrittenBy;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Bipush.bipush;

public class BipushTest implements WithAssertions {

	private static final ByteValue BYTE_TO_PUSH = ByteValue.byteValue(99);

	@Test
	public void writesOpcodeAndByteArgument() throws Exception {
		assertThat(bytesWrittenBy(bipush(BYTE_TO_PUSH))).containsExactly(bytes((byte) 0x10, bytesWrittenBy(BYTE_TO_PUSH)));
	}

	@Test
	public void isTwoBytesLong() throws Exception {
		assertThat(bipush(BYTE_TO_PUSH).sizeInBytes()).isEqualTo(2);
	}
}
