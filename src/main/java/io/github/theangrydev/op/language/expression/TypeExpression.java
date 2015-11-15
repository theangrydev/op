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
import io.github.theangrydev.op.generation.VariableReference;
import io.github.theangrydev.opper.scanner.Location;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class TypeExpression implements Expression {
	private final Location location;
	private final String type;
	private VariableReference<?> variableReference;

	private TypeExpression(Location location, String type) {
		this.location = location;
		this.type = type;
	}

	public static TypeExpression typeExpression(Location location, String type) {
		return new TypeExpression(location, type);
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return type;
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public Expression simplify() {
		return this;
	}

	@Override
	public void checkTypes(ProgramCompiler programCompiler) {
		Optional<VariableReference<?>> variableReference = programCompiler.lookupVariableReference(type);
		Preconditions.checkState(variableReference.isPresent(), "There is no variable with type '%s'", type);
		this.variableReference = variableReference.get();
	}

	@Override
	public void compile(ProgramCompiler programCompiler) {
		variableReference.load(programCompiler);
	}

	@Override
	public Optional<Expression> simplifyAddToRight(Expression right) {
		return right.simplifyAddToLeft(this);
	}

	@Override
	public Optional<Expression> simplifyAddToLeft(RealConstant left) {
		return simplifyAddTypeToLeft(left);
	}

	@Override
	public Optional<Expression> simplifyAddToLeft(IntegerConstant left) {
		return simplifyAddTypeToLeft(left);
	}

	public Optional<Expression> simplifyAddTypeToLeft(NumericConstant<?> left) {
		if (left.isZero()) {
			return of(typeExpression(locationBetween(left, this), type));
		}
		return empty();
	}

	public Optional<Expression> simplifyAddTypeToRight(NumericConstant<?> right) {
		if (right.isZero()) {
			return of(typeExpression(locationBetween(this, right), type));
		}
		return empty();
	}

	@Override
	public VariableReference<?> typeReference() {
		return variableReference;
	}
}
