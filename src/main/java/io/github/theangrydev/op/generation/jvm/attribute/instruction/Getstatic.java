/**
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
package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import io.github.theangrydev.op.generation.jvm.ByteValue;
import io.github.theangrydev.op.generation.jvm.constant.ConstantPoolIndex;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.ByteValue.byteValue;

public class Getstatic implements Instruction {

	private static final ByteValue GETSTATIC = byteValue(0xb2);
	private final ConstantPoolIndex fieldReference;

	private Getstatic(ConstantPoolIndex fieldReference) {
		this.fieldReference = fieldReference;
	}

	public static Getstatic getstatic(ConstantPoolIndex fieldReference) {
		return new Getstatic(fieldReference);
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		GETSTATIC.writeTo(dataOutput);
		fieldReference.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return GETSTATIC.sizeInBytes() + fieldReference.sizeInBytes();
	}
}
