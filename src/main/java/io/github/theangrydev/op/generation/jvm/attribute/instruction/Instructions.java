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
package io.github.theangrydev.op.generation.jvm.attribute.instruction;

import com.google.common.base.Preconditions;
import io.github.theangrydev.op.generation.jvm.ClassFileWriter;
import io.github.theangrydev.op.generation.jvm.IntValue;
import io.github.theangrydev.op.generation.jvm.WithSizeInBytes;

import java.io.DataOutput;
import java.io.IOException;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static io.github.theangrydev.op.generation.jvm.IntValue.intValue;

public class Instructions implements ClassFileWriter, WithSizeInBytes {
	private static final int MAX_INSTRUCTION_BYTES = 65535;

	private final List<Instruction> instructions;
	private final IntValue sizeOfInstructionsInBytes;

	private Instructions(List<Instruction> instructions, IntValue sizeOfInstructionsInBytes) {
		this.instructions = instructions;
		this.sizeOfInstructionsInBytes = sizeOfInstructionsInBytes;
	}

	public static Instructions instructions(Instruction... instructions) {
		return instructions(newArrayList(instructions));
	}

	public static Instructions instructions(List<Instruction> instructions) {
		Preconditions.checkState(!instructions.isEmpty(), "Code must have at least one operation!");

		int sizeOfInstructionsInBytes = instructions.stream().mapToInt(Instruction::sizeInBytes).sum();
		Preconditions.checkState(sizeOfInstructionsInBytes <= MAX_INSTRUCTION_BYTES, "Total size of instructions must be less than %s bytes but was %s", MAX_INSTRUCTION_BYTES, sizeOfInstructionsInBytes);

		return new Instructions(instructions, intValue(sizeOfInstructionsInBytes));
	}

	@Override
	public int sizeInBytes() {
		return sizeOfInstructionsInBytes.sizeInBytes() + sizeOfInstructionsInBytes.value();
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		sizeOfInstructionsInBytes.writeTo(dataOutput);
		for (Instruction instruction : instructions) {
			instruction.writeTo(dataOutput);
		}
	}
}
