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
package io.github.theangrydev.op.generation.jvm;

import com.google.common.base.Preconditions;

import java.io.DataOutput;
import java.io.IOException;

public class ShortValue implements ClassFileWriter, WithSizeInBytes {

	private static final int MAX_SHORT_VALUE = 65535;
	private final int value;

	private ShortValue(int value) {
		this.value = value;
	}

	public static ShortValue shortValue(int value) {
		Preconditions.checkArgument(value >= 0, "%s may not be negative but was %s", name(), value);
		Preconditions.checkArgument(value <= MAX_SHORT_VALUE, "%s can be at most %s but was %s", name(), MAX_SHORT_VALUE, value);
		return new ShortValue(value);
	}

	private static String name() {
		return ShortValue.class.getSimpleName();
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		dataOutput.writeShort(value);
	}

	@Override
	public int sizeInBytes() {
		return 2;
	}

	public int value() {
		return value;
	}
}
