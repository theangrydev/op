/*
 * Copyright 2015-2020 Liam Williams <liam.williams@zoho.com>.
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
package io.github.theangrydev.op.language.assignment;

import io.github.theangrydev.op.generation.ProgramCompiler;
import io.github.theangrydev.op.generation.VariableReference;
import io.github.theangrydev.op.language.ProgramElement;
import io.github.theangrydev.op.language.expression.TypeExpression;
import io.github.theangrydev.opper.scanner.Location;

public class TypeDeclaration implements ProgramElement<TypeDeclaration> {

	private final Location location;
	private final TypeExpression targetType;
	private final Type existingType;
	private VariableReference<?> variableReference;

	private TypeDeclaration(TypeExpression targetType, Type existingType) {
		this.location = locationBetween(targetType, existingType);
		this.targetType = targetType;
		this.existingType = existingType;
	}

	public static TypeDeclaration of(TypeExpression targetType, Type existingType) {
		return new TypeDeclaration(targetType, existingType);
	}

	public TypeExpression getTargetType() {
		return targetType;
	}

	public Type getExistingType() {
		return existingType;
	}

	@Override
	public String toString() {
		return targetType + ":" + existingType;
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public TypeDeclaration simplify() {
		return this;
	}

	@Override
	public void checkTypes(ProgramCompiler programCompiler) {
		existingType.checkTypes(programCompiler);
		variableReference = programCompiler.registerVariableReference(targetType.getType(), existingType.underlyingType());
	}

	@Override
	public void compile(ProgramCompiler programCompiler) {
		//TODO: compile default expression here for the underlying type
		variableReference.store(programCompiler);
	}
}
