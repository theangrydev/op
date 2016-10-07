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

import io.github.theangrydev.op.generation.jvm.ShortValue;
import io.github.theangrydev.op.generation.jvm.attribute.instruction.Instructions;

import java.io.DataOutput;
import java.io.IOException;

public class CodeAttributeInfo implements AttributeInfo {
	private final ShortValue maxStackDepth;
	private final ShortValue maxLocalVariables;
	private final Instructions instructions;
	private final ExceptionTable exceptionTable;
	private final Attributes attributes;

	private CodeAttributeInfo(ShortValue maxStackDepth, ShortValue maxLocalVariables, Instructions instructions, ExceptionTable exceptionTable, Attributes attributes) {
		this.maxStackDepth = maxStackDepth;
		this.maxLocalVariables = maxLocalVariables;
		this.instructions = instructions;
		this.exceptionTable = exceptionTable;
		this.attributes = attributes;
	}

	public static CodeAttributeInfo codeAttributeInfo(ShortValue maxStackDepth, ShortValue maxLocalVariables, Instructions instructions, ExceptionTable exceptionTable, Attributes attributes) {
		return new CodeAttributeInfo(maxStackDepth, maxLocalVariables, instructions, exceptionTable, attributes);
	}

	@Override
	public int sizeInBytes() {
		return maxStackDepth.sizeInBytes() + maxLocalVariables.sizeInBytes() + instructions.sizeInBytes() + exceptionTable.sizeInBytes() + attributes.sizeInBytes();
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		maxStackDepth.writeTo(dataOutput);
		maxLocalVariables.writeTo(dataOutput);
		instructions.writeTo(dataOutput);
		exceptionTable.writeTo(dataOutput);
		attributes.writeTo(dataOutput);
	}
}
