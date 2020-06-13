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

import io.github.theangrydev.op.generation.jvm.ByteValue;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.ByteValue.byteValue;

public class VoidReturn implements Instruction {
	private static final ByteValue RETURN = byteValue(0xb1);

	private VoidReturn() {}

	public static VoidReturn voidreturn() {
		return new VoidReturn();
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		RETURN.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return RETURN.sizeInBytes();
	}
}
