package io.github.theangrydev.op.language;

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
