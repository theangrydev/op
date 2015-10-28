package io.github.theangrydev.op.generation.jvm.method;

import io.github.theangrydev.op.generation.jvm.ClassFileWriter;
import io.github.theangrydev.op.generation.jvm.ShortValue;

import java.io.DataOutput;
import java.io.IOException;
import java.util.List;

import static io.github.theangrydev.op.generation.jvm.ShortValue.shortValue;

public class Methods implements ClassFileWriter {

	private final ShortValue numberOfMethods;
	private final List<MethodInfo> methods;

	private Methods(ShortValue numberOfMethods, List<MethodInfo> methods) {
		this.numberOfMethods = numberOfMethods;
		this.methods = methods;
	}

	public static Methods methods(List<MethodInfo> methods) {
		ShortValue numberOfMethods = shortValue(methods.size());
		return new Methods(numberOfMethods, methods);
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		numberOfMethods.writeTo(dataOutput);
		for (MethodInfo method : methods) {
			method.writeTo(dataOutput);
		}
	}
}
