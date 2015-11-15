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
package io.github.theangrydev.op.generation.jvm.constant;

import io.github.theangrydev.op.common.WithReflectiveEqualsAndHashCode;
import io.github.theangrydev.op.generation.jvm.IntValue;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.IntValue.intValue;

public class IntegerConstantInfo extends WithReflectiveEqualsAndHashCode implements ConstantInfo {

	private final IntValue value;

	private IntegerConstantInfo(IntValue value) {
		this.value = value;
	}

	public static IntegerConstantInfo integerConstant(int value) {
		return new IntegerConstantInfo(intValue(value));
	}

	@Override
	public byte tag() {
		return 3;
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		value.writeTo(dataOutput);
	}
}
