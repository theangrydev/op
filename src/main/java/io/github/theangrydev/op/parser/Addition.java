package io.github.theangrydev.op.parser;

import io.github.theangrydev.opper.scanner.Location;

public class Addition implements BinaryOperator {

	private final Location location;
	private final Expression left;
	private final Expression right;

	private Addition(Location location, Expression left, Expression right) {
		this.location = location;
		this.left = left;
		this.right = right;
	}

	public static Addition add(Expression left, Expression right) {
		return new Addition(ProgramElement.locationBetween(left, right), left, right);
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
	public void accept(Visitor visitor) {
		visitor.visit(this);
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
