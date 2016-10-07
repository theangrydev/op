/*
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

import com.google.common.collect.ImmutableSet;

import java.util.Set;

import static io.github.theangrydev.op.generation.IntegerType.INTEGER_TYPE;
import static io.github.theangrydev.op.generation.RealType.REAL_TYPE;
import static io.github.theangrydev.op.generation.StringType.STRING_TYPE;

public interface UnderlyingType<T extends UnderlyingType<T>> {
	void store(ProgramCompiler programCompiler, VariableReference<T> variableReference);
	void load(ProgramCompiler programCompiler, VariableReference<T> variableReference);
	void load(ProgramCompiler programCompiler, ConstantReference<T> constantReference);
	void add(ProgramCompiler programCompiler);
	boolean supportsAdd();
	String name();
	Set<UnderlyingType<?>> DEFAULT_TYPES = ImmutableSet.of(INTEGER_TYPE, REAL_TYPE, STRING_TYPE);
	VariableReference<T> variableReference(int variableIndex, String targetTypeName);
}
