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

public class StringType implements UnderlyingType<StringType> {

	public static StringType STRING_TYPE = new StringType();

	private StringType() {}

	@Override
	public void store(ProgramCompiler programCompiler, VariableReference<StringType> variableReference) {
		programCompiler.storeString(variableReference);
	}

	@Override
	public void load(ProgramCompiler programCompiler, VariableReference<StringType> variableReference) {
		programCompiler.loadStringFromVariable(variableReference);
	}

	@Override
	public void load(ProgramCompiler programCompiler, ConstantReference<StringType> constantReference) {
		programCompiler.loadStringFromConstant(constantReference);
	}

	@Override
	public void add(ProgramCompiler programCompiler) {
		programCompiler.addTwoStrings();
	}

	@Override
	public boolean supportsAdd() {
		return true;
	}

	@Override
	public String name() {
		return "String";
	}

	@Override
	public String toString() {
		return name();
	}

	@Override
	public VariableReference<StringType> variableReference(int variableIndex, String targetTypeName) {
		return VariableReference.variableReference(variableIndex, targetTypeName, this);
	}
}
