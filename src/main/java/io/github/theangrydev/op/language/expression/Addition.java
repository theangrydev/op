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
package io.github.theangrydev.op.language.expression;

import com.google.common.base.Preconditions;
import io.github.theangrydev.op.generation.ProgramCompiler;
import io.github.theangrydev.op.generation.TypeReference;
import io.github.theangrydev.opper.scanner.Location;

public class Addition implements BinaryOperator {

	private final Location location;
	private Expression left;
	private Expression right;
	private TypeReference typeReference;

	private Addition(Expression left, Expression right) {
		this.location = locationBetween(left, right);
		this.left = left;
		this.right = right;
	}

	public static Addition add(Expression left, Expression right) {
		return new Addition(left, right);
	}

	@Override
	public Expression simplify() {
		left = left.simplify();
		right = right.simplify();
		return left.simplifyAddToRight(right).orElse(this);
	}

	@Override
	public void checkTypes(ProgramCompiler programCompiler) {
		left.checkTypes(programCompiler);
		right.checkTypes(programCompiler);

		TypeReference leftType = left.typeReference();
		TypeReference rightType = right.typeReference();
		Preconditions.checkState(leftType == rightType, "Left type '%s' should match right type '%s'", leftType, rightType);
		typeReference = leftType;

		Preconditions.checkState(typeReference.underlyingType().supportsAdd(), "Type '%s' does not support addition", typeReference);
	}

	@Override
	public void compile(ProgramCompiler programCompiler) {
		left.compile(programCompiler);
		right.compile(programCompiler);
		underlyingType().add(programCompiler);
	}

	@Override
	public Expression getLeft() {
		return left;
	}

	@Override
	public Expression getRight() {
		return right;
	}

	@Override
	public String toString() {
		return left + "+" + right;
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public TypeReference typeReference() {
		return typeReference;
	}
}
