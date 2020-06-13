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

public class Iload0 implements Instruction {
	private static final ByteValue ILOAD0 = byteValue(0x1a);

	private Iload0() {}

	public static Iload0 iload0() {
		return new Iload0();
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		ILOAD0.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return ILOAD0.sizeInBytes();
	}
}
