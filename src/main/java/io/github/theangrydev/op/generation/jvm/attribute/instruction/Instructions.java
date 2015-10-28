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
