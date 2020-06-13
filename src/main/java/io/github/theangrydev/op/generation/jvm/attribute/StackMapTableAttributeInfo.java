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
