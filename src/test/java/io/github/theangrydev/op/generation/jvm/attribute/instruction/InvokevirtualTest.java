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
package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.common.WithAssertions;
import io.github.theangrydev.op.generation.jvm.constant.ConstantPoolIndex;
import org.junit.Test;

import static io.github.theangrydev.op.generation.ByteWriter.bytes;
import static io.github.theangrydev.op.generation.ByteWriter.bytesWrittenBy;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Invokevirtual.invokevirtual;
import static io.github.theangrydev.op.generation.jvm.constant.ConstantPoolIndex.constantPoolIndex;

public class InvokevirtualTest implements WithAssertions {

	private static final ConstantPoolIndex METHOD_REFERENCE = constantPoolIndex(9999);

	@Test
	public void writesOpcodeAndIndexBytes() throws Exception {
		assertThat(bytesWrittenBy(invokevirtual(METHOD_REFERENCE))).containsExactly(bytes((byte) 0xb6, bytesWrittenBy(METHOD_REFERENCE)));
	}

	@Test
	public void isThreeBytesLong() throws Exception {
		assertThat(invokevirtual(METHOD_REFERENCE).sizeInBytes()).isEqualTo(3);
	}
}
