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


public class RealType implements UnderlyingType<RealType> {

	public static final RealType REAL_TYPE = new RealType();

	private RealType() {}

	@Override
	public void store(ProgramCompiler programCompiler, VariableReference<RealType> variableReference) {
		programCompiler.storeReal(variableReference);
	}

	@Override
	public void load(ProgramCompiler programCompiler, VariableReference<RealType> variableReference) {
		programCompiler.loadRealFromVariable(variableReference);
	}

	@Override
	public void load(ProgramCompiler programCompiler, ConstantReference<RealType> constantReference) {
		programCompiler.loadRealFromConstant(constantReference);
	}

	@Override
	public void add(ProgramCompiler programCompiler) {
		programCompiler.addTwoReals();
	}

	@Override
	public boolean supportsAdd() {
		return true;
	}

	@Override
	public String name() {
		return "Real";
	}

	@Override
	public String toString() {
		return name();
	}

	@Override
	public VariableReference<RealType> variableReference(int variableIndex, String targetTypeName) {
		return VariableReference.variableReference(variableIndex, targetTypeName, this);
	}
}
