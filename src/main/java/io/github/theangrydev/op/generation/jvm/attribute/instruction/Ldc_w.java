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

import static io.github.theangrydev.op.generation.jvm.ByteValue.MAX_BYTE_VALUE;
import static io.github.theangrydev.op.generation.jvm.ByteValue.byteValue;

public class Ldc_w implements Instruction {

	private static final ByteValue LDC_W = byteValue(0x13);
	private final ByteValue indexByte1;
	private final ByteValue indexByte2;

	private Ldc_w(ByteValue indexByte1, ByteValue indexByte2) {
		this.indexByte1 = indexByte1;
		this.indexByte2 = indexByte2;
	}

	public static Ldc_w ldc_w(int index) {
		return new Ldc_w(byteValue(index / MAX_BYTE_VALUE), byteValue(index % MAX_BYTE_VALUE));
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		LDC_W.writeTo(dataOutput);
		indexByte1.writeTo(dataOutput);
		indexByte2.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return LDC_W.sizeInBytes() + indexByte1.sizeInBytes() + indexByte2.sizeInBytes();
	}
}
