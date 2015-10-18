package io.github.theangrydev.op.language;

import io.github.theangrydev.opper.scanner.Location;

import java.util.Optional;

public class Addition implements BinaryOperator {

	private final Location location;
	private Expression left;
	private Expression right;

	private Addition(Location location, Expression left, Expression right) {
		this.location = location;
		this.left = left;
		this.right = right;
	}

	public static Addition add(Expression left, Expression right) {
		return new Addition(ProgramElement.locationBetween(left, right), left, right);
	}

	@Override
	public Expression simplify() {
		left = left.simplify();
		right = right.simplify();
		return left.addToRight(right).orElse(this);
	}

	@Override
	public Optional<Expression> addToRight(Expression right) {
		return Optional.empty();
	}

	@Override
	public Optional<Expression> addToLeft(RealConstant left) {
		return Optional.empty();
	}

	@Override
	public Optional<Expression> addToLeft(IntegerConstant left) {
		return Optional.empty();
	}

	@Override
	public Optional<Expression> addToLeft(StringConstant left) {
		return Optional.empty();
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
}
