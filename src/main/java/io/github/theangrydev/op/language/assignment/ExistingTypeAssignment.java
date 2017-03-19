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
package io.github.theangrydev.op.language.assignment;


import com.google.common.base.Preconditions;
import io.github.theangrydev.op.generation.ProgramCompiler;
import io.github.theangrydev.op.generation.TypeReference;
import io.github.theangrydev.op.language.expression.Expression;
import io.github.theangrydev.op.language.expression.TypeExpression;
import io.github.theangrydev.opper.scanner.Location;

public class ExistingTypeAssignment implements Assignment<ExistingTypeAssignment> {
	private final Location location;
	private final TypeExpression targetType;
	private Expression expression;

	private ExistingTypeAssignment(TypeExpression targetType, Expression expression) {
		this.location = locationBetween(targetType, expression);
		this.targetType = targetType;
		this.expression = expression;
	}

	public static ExistingTypeAssignment of(TypeExpression targetType, Expression expression) {
		return new ExistingTypeAssignment(targetType, expression);
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
		return targetType + "=" + expression;
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public ExistingTypeAssignment simplify() {
		expression = expression.simplify();
		return this;
	}

	@Override
	public void checkTypes(ProgramCompiler programCompiler) {
		expression.checkTypes(programCompiler);
		targetType.checkTypes(programCompiler);
		TypeReference<?> targetTypeReference = targetType.typeReference();
		TypeReference<?> expressionTypeReference = expression.typeReference();
		Preconditions.checkState(targetTypeReference == expressionTypeReference, "Target type '%s' should match expression type '%s'", targetTypeReference, expressionTypeReference);
	}

	@Override
	public void compile(ProgramCompiler programCompiler) {
		expression.compile(programCompiler);
		targetType.typeReference().store(programCompiler);
	}
}
