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
