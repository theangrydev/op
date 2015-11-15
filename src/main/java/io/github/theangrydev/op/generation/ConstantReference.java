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
package io.github.theangrydev.op.generation;

import io.github.theangrydev.op.generation.jvm.constant.ConstantPoolIndex;

public class ConstantReference<T extends UnderlyingType<T>> implements TypeReference<T> {
	private final ConstantPoolIndex index;
	private final T underlyingType;

	private ConstantReference(ConstantPoolIndex index, T underlyingType) {
		this.index = index;
		this.underlyingType = underlyingType;
	}

	public static <T extends UnderlyingType<T>> ConstantReference<T> constantReference(ConstantPoolIndex index, T underlyingType) {
		return new ConstantReference<>(index, underlyingType);
	}

	@Override
	public T underlyingType() {
		return underlyingType;
	}

	@Override
	public String toString() {
		return underlyingType.name() + "[" + index + "]";
	}

	@Override
	public void load(ProgramCompiler programCompiler) {
		underlyingType.load(programCompiler, this);
	}
}
