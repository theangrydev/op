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

public class Bipush implements Instruction {

	private static final ByteValue BIPUSH = byteValue(0x10);
	private final ByteValue byteToPush;

	private Bipush(ByteValue byteToPush) {
		this.byteToPush = byteToPush;
	}

	public static Bipush bipush(ByteValue byteToPush) {
		return new Bipush(byteToPush);
	}

	public static Bipush bipush(int byteToPush) {
		return bipush(byteValue(byteToPush));
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		BIPUSH.writeTo(dataOutput);
		byteToPush.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return BIPUSH.sizeInBytes() + byteToPush.sizeInBytes();
	}
}
