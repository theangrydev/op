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
package io.github.theangrydev.op.generation;

public class ObjectType implements UnderlyingType<ObjectType> {

	private final String name;

	private ObjectType(String name) {
		this.name = name;
	}

	public static ObjectType underlyingType(String name) {
		return new ObjectType(name);
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public void store(ProgramCompiler programCompiler, VariableReference<ObjectType> variableReference) {
		programCompiler.storeObject(variableReference);
	}

	@Override
	public void load(ProgramCompiler programCompiler, VariableReference<ObjectType> variableReference) {
		programCompiler.loadObjectFromVariable(variableReference);
	}

	@Override
	public void load(ProgramCompiler programCompiler, ConstantReference<ObjectType> constantReference) {
		programCompiler.loadObjectFromConstant(constantReference);
	}

	@Override
	public VariableReference<ObjectType> variableReference(int variableIndex, String targetTypeName) {
		return VariableReference.variableReference(variableIndex, targetTypeName, this);
	}

	@Override
	public void add(ProgramCompiler programCompiler) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean supportsAdd() {
		return false;
	}
}
