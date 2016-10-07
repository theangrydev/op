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
package io.github.theangrydev.op.language.assignment;

import io.github.theangrydev.op.generation.ProgramCompiler;
import io.github.theangrydev.op.generation.VariableReference;
import io.github.theangrydev.op.language.expression.Expression;
import io.github.theangrydev.op.language.expression.TypeExpression;
import io.github.theangrydev.opper.scanner.Location;

public class TypeDeclarationAssignment implements Assignment<TypeDeclarationAssignment> {
	private final Location location;
	private final Type existingType;
	private final TypeExpression targetType;
	private Expression expression;
	private VariableReference<?> variableReference;

	private TypeDeclarationAssignment(TypeExpression targetType, Type existingType, Expression expression) {
		this.location = locationBetween(targetType, existingType);
		this.existingType = existingType;
		this.targetType = targetType;
		this.expression = expression;
	}

	public static TypeDeclarationAssignment of(TypeDeclaration typeDeclaration, Expression expression) {
		return new TypeDeclarationAssignment(typeDeclaration.getTargetType(), typeDeclaration.getExistingType(), expression);
	}

	public Type getExistingType() {
		return existingType;
	}

	@Override
	public TypeExpression getTargetType() {
		return targetType;
	}

	@Override
	public Expression getExpression() {
		return expression;
	}

	@Override
	public String toString() {
		return targetType + ":" + existingType + "=" + expression;
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public void checkTypes(ProgramCompiler programCompiler) {
		expression.checkTypes(programCompiler);
		existingType.checkTypes(programCompiler);
		variableReference = programCompiler.registerVariableReference(targetType.getType(), existingType.underlyingType());
	}

	@Override
	public void compile(ProgramCompiler programCompiler) {
		expression.compile(programCompiler);
		variableReference.store(programCompiler);
	}

	@Override
	public TypeDeclarationAssignment simplify() {
		expression = expression.simplify();
		return this;
	}
}
