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
import io.github.theangrydev.op.generation.jvm.ByteValue;
import io.github.theangrydev.op.generation.jvm.ClassFileWriter;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.ByteValue.byteValue;

public class Constant extends WithReflectiveEqualsAndHashCode implements ClassFileWriter {

	private final ByteValue tag;
	private final ConstantInfo constantInfo;

	private Constant(ByteValue tag, ConstantInfo constantInfo) {
		this.tag = tag;
		this.constantInfo = constantInfo;
	}

	public static Constant constant(ConstantInfo constantInfo) {
		ByteValue tag = byteValue(constantInfo.tag());
		return new Constant(tag, constantInfo);
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		tag.writeTo(dataOutput);
		constantInfo.writeTo(dataOutput);
	}
}
