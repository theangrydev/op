package io.github.theangrydev.op.language.expression;

import io.github.theangrydev.op.generation.ProgramCompiler;
import io.github.theangrydev.op.generation.VariableReference;
import io.github.theangrydev.opper.scanner.Location;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class TypeExpression implements Expression {
	private final Location location;
	private final String type;
	private VariableReference variableReference;

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
		variableReference = programCompiler.lookupVariableReference(type);
	}

	@Override
	public void compile(ProgramCompiler programCompiler) {
		underlyingType().load(programCompiler, variableReference);
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
	public VariableReference typeReference() {
		return variableReference;
	}
}
