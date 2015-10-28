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
