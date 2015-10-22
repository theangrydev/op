package io.github.theangrydev.op.language;


import io.github.theangrydev.opper.scanner.Location;

import java.util.Optional;

import static java.lang.Integer.parseInt;

public class IntegerConstant implements NumericConstant<Integer>, SimplifyingConstantAddition {
	private final Location location;
	private final int value;

	private IntegerConstant(Location location, int value) {
		this.location = location;
		this.value = value;
	}

	public static IntegerConstant integerConstant(Location location, String value) {
		return integerConstant(location, parseInt(value));
	}

	public static IntegerConstant integerConstant(Location location, int value) {
		return new IntegerConstant(location, value);
	}

	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
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
	public Optional<Expression> simplifyAddToRight(Expression right) {
		return right.simplifyAddToLeft(this);
	}

	@Override
	public Optional<Expression> simplifyAddToLeft(TypeExpression left) {
		return left.simplifyAddTypeToRight(this);
	}

	@Override
	public Expression addToLeft(RealConstant left) {
		return left.addRealToRight(this);
	}

	@Override
	public Expression addToLeft(IntegerConstant left) {
		return integerConstant(locationBetween(left, this), value + left.getValue());
	}

	@Override
	public Expression addToLeft(StringConstant left) {
		return left.concatenateToRight(this);
	}

	@Override
	public boolean isZero() {
		return value == 0;
	}
}
