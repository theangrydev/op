package io.github.theangrydev.op.language;

import io.github.theangrydev.opper.scanner.Location;

public class Addition implements BinaryOperator {

	private final Location location;
	private Expression left;
	private Expression right;

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
		return left.addToRight(right).orElse(this);
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
