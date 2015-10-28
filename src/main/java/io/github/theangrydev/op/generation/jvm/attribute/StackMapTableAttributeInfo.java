package io.github.theangrydev.op.generation.jvm.attribute;

import io.github.theangrydev.op.generation.jvm.ShortValue;

import java.io.DataOutput;
import java.io.IOException;
import java.util.List;

import static io.github.theangrydev.op.generation.jvm.ShortValue.shortValue;

public class StackMapTableAttributeInfo implements AttributeInfo {

	private final List<StackMapFrame> stackMapFrames;
	private final ShortValue numberOfEntries;

	private StackMapTableAttributeInfo(List<StackMapFrame> stackMapFrames, ShortValue numberOfEntries) {
		this.stackMapFrames = stackMapFrames;
		this.numberOfEntries = numberOfEntries;
	}

	public static StackMapTableAttributeInfo stackMapTableAttributeInfo(List<StackMapFrame> stackMapFrames) {
		ShortValue numberOfEntries = shortValue(stackMapFrames.size());
		return new StackMapTableAttributeInfo(stackMapFrames, numberOfEntries);
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		numberOfEntries.writeTo(dataOutput);
		for (StackMapFrame stackMapFrame : stackMapFrames) {
			stackMapFrame.writeTo(dataOutput);
		}
	}

	@Override
	public int sizeInBytes() {
		return numberOfEntries.sizeInBytes() + stackMapFrames.stream().mapToInt(StackMapFrame::sizeInBytes).sum();
	}
}
