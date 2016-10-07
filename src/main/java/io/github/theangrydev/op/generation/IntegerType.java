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


public class IntegerType implements UnderlyingType<IntegerType> {

	public static final IntegerType INTEGER_TYPE = new IntegerType();

	private IntegerType() {}

	@Override
	public void store(ProgramCompiler programCompiler, VariableReference<IntegerType> variableReference) {
		programCompiler.storeInteger(variableReference);
	}

	@Override
	public void load(ProgramCompiler programCompiler, VariableReference<IntegerType> variableReference) {
		programCompiler.loadIntegerFromVariable(variableReference);
	}

	@Override
	public void load(ProgramCompiler programCompiler, ConstantReference<IntegerType> constantReference) {
		programCompiler.loadIntegerFromConstant(constantReference);
	}

	@Override
	public void add(ProgramCompiler programCompiler) {
		programCompiler.addTwoIntegers();
	}

	@Override
	public boolean supportsAdd() {
		return true;
	}

	@Override
	public String name() {
		return "Integer";
	}

	@Override
	public String toString() {
		return name();
	}

	@Override
	public VariableReference<IntegerType> variableReference(int variableIndex, String targetTypeName) {
		return VariableReference.variableReference(variableIndex, targetTypeName, this);
	}
}
