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
package io.github.theangrydev.op.generation.jvm.attribute;

import io.github.theangrydev.op.generation.jvm.ClassFileWriter;
import io.github.theangrydev.op.generation.jvm.IntValue;
import io.github.theangrydev.op.generation.jvm.WithSizeInBytes;
import io.github.theangrydev.op.generation.jvm.constant.ConstantPoolIndex;
import io.github.theangrydev.op.generation.jvm.constant.Utf8InfoConstant;

import java.io.DataOutput;
import java.io.IOException;

import static io.github.theangrydev.op.generation.jvm.IntValue.intValue;

public class Attribute implements ClassFileWriter, WithSizeInBytes {
	private final ConstantPoolIndex<Utf8InfoConstant> attributeNameIndex;
	private final AttributeInfo attributeInfo;
	private final IntValue attributeLengthInBytes;

	private Attribute(ConstantPoolIndex<Utf8InfoConstant> attributeNameIndex, AttributeInfo attributeInfo, IntValue attributeLengthInBytes) {
		this.attributeNameIndex = attributeNameIndex;
		this.attributeInfo = attributeInfo;
		this.attributeLengthInBytes = attributeLengthInBytes;
	}

	public static Attribute attribute(ConstantPoolIndex<Utf8InfoConstant> attributeNameIndex, AttributeInfo attributeInfo) {
		IntValue attributeLengthInBytes = intValue(attributeInfo.sizeInBytes());
		return new Attribute(attributeNameIndex, attributeInfo, attributeLengthInBytes);
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		attributeNameIndex.writeTo(dataOutput);
		attributeLengthInBytes.writeTo(dataOutput);
		attributeInfo.writeTo(dataOutput);
	}

	@Override
	public int sizeInBytes() {
		return attributeLengthInBytes.sizeInBytes() + attributeNameIndex.sizeInBytes() + attributeInfo.sizeInBytes();
	}
}
