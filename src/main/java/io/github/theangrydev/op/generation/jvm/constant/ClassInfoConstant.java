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
package io.github.theangrydev.op.generation.jvm.constant;

import io.github.theangrydev.op.common.WithReflectiveEqualsAndHashCode;

import java.io.DataOutput;
import java.io.IOException;

public class ClassInfoConstant extends WithReflectiveEqualsAndHashCode implements ConstantInfo {

	private final ConstantPoolIndex<Utf8InfoConstant> nameIndex;

	private ClassInfoConstant(ConstantPoolIndex<Utf8InfoConstant> nameIndex) {
		this.nameIndex = nameIndex;
	}

	public static ClassInfoConstant classInfo(ConstantPoolIndex<Utf8InfoConstant> constantPoolIndex) {
		return new ClassInfoConstant(constantPoolIndex);
	}

	@Override
	public byte tag() {
		return 7;
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		nameIndex.writeTo(dataOutput);
	}
}
