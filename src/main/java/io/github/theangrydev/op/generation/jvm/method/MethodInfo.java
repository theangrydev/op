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
package io.github.theangrydev.op.generation.jvm.method;

import io.github.theangrydev.op.generation.jvm.ClassFileWriter;
import io.github.theangrydev.op.generation.jvm.attribute.Attributes;
import io.github.theangrydev.op.generation.jvm.constant.ConstantPoolIndex;

import java.io.DataOutput;
import java.io.IOException;

public class MethodInfo implements ClassFileWriter {

	private final MethodAccessFlags methodAccessFlags;
	private final ConstantPoolIndex nameIndex;
	private final ConstantPoolIndex descriptorIndex;
	private final Attributes attributes;

	private MethodInfo(MethodAccessFlags methodAccessFlags, ConstantPoolIndex nameIndex, ConstantPoolIndex descriptorIndex, Attributes attributes) {
		this.methodAccessFlags = methodAccessFlags;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.attributes = attributes;
	}

	public static MethodInfo methodInfo(ConstantPoolIndex nameIndex, ConstantPoolIndex descriptorIndex, Attributes attributes, MethodAccessFlags methodAccessFlags) {
		return new MethodInfo(methodAccessFlags, nameIndex, descriptorIndex, attributes);
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		methodAccessFlags.writeTo(dataOutput);
		nameIndex.writeTo(dataOutput);
		descriptorIndex.writeTo(dataOutput);
		attributes.writeTo(dataOutput);
	}
}
