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

import io.github.theangrydev.op.generation.jvm.ByteValue;
import io.github.theangrydev.op.generation.jvm.constant.ConstantPoolIndex;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.ByteValue.byteValue;

public class Invokespecial implements Instruction {

	private static final ByteValue INVOKESPECIAL = byteValue(0xb7);
	private final ConstantPoolIndex methodReference;

	private Invokespecial(ConstantPoolIndex methodReference) {
		this.methodReference = methodReference;
	}

	public static Invokespecial invokespecial(ConstantPoolIndex methodReference) {
		return new Invokespecial(methodReference);
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		INVOKESPECIAL.writeTo(dataOutput);
		methodReference.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return INVOKESPECIAL.sizeInBytes() + methodReference.sizeInBytes();
	}
}
