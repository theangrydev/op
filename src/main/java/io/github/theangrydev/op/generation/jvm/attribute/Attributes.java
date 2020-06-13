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

import io.github.theangrydev.op.generation.jvm.ClassFileWriter;
import io.github.theangrydev.op.generation.jvm.ShortValue;
import io.github.theangrydev.op.generation.jvm.WithSizeInBytes;

import java.io.DataOutput;
import java.io.IOException;
import java.util.List;

import static io.github.theangrydev.op.generation.jvm.ShortValue.shortValue;

public class Attributes implements ClassFileWriter, WithSizeInBytes {

	private final ShortValue numberOfAttributes;
	private final List<Attribute> attributes;

	private Attributes(List<Attribute> attributes, ShortValue numberOfAttributes) {
		this.attributes = attributes;
		this.numberOfAttributes = numberOfAttributes;
	}

	public static Attributes attributes(List<Attribute> attributes) {
		ShortValue numberOfAttributes = shortValue(attributes.size());
		return new Attributes(attributes, numberOfAttributes);
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		numberOfAttributes.writeTo(dataOutput);
		for (Attribute attribute : attributes) {
			attribute.writeTo(dataOutput);
		}
	}

	@Override
	public int sizeInBytes() {
		return numberOfAttributes.sizeInBytes() + attributes.stream().mapToInt(Attribute::sizeInBytes).sum();
	}
}
