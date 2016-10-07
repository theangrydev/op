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

import io.github.theangrydev.op.generation.jvm.ClassFileWriter;
import io.github.theangrydev.op.generation.jvm.ShortValue;
import io.github.theangrydev.op.generation.jvm.WithSizeInBytes;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.ShortValue.shortValue;

@SuppressWarnings("unused") // Type is used to ensure type safety
public class ConstantPoolIndex<T> implements ClassFileWriter, WithSizeInBytes {

	private final ShortValue index;

	private ConstantPoolIndex(ShortValue index) {
		this.index = index;
	}

	public static <T> ConstantPoolIndex<T> constantPoolIndex(int index) {
		return new ConstantPoolIndex<>(shortValue(index));
	}

	public int index() {
		return index.value();
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		index.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return index.sizeInBytes();
	}
}
