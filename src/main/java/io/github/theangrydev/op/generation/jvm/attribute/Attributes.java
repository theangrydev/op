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
