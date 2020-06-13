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
package io.github.theangrydev.op.generation.jvm.constant;

import io.github.theangrydev.op.generation.jvm.attribute.instruction.Instruction;

import static io.github.theangrydev.op.generation.jvm.ByteValue.MAX_BYTE_VALUE;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Ldc.ldc;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Ldc_w.ldc_w;

@SuppressWarnings("unused") // Type is used to ensure type safety
public class ConstantPoolIndex<T> {

	private final int index;

	private ConstantPoolIndex(int index) {
		this.index = index;
	}

	public static <T> ConstantPoolIndex<T> constantPoolIndex(int index) {
		return new ConstantPoolIndex<>(index);
	}

	public int index() {
		return index;
	}

	public Instruction loadInstruction() {
		if (index < MAX_BYTE_VALUE) {
			return ldc(index);
		} else {
			return ldc_w(index);
		}
	}
}
