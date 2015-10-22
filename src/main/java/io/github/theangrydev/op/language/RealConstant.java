package io.github.theangrydev.op.language;

import io.github.theangrydev.opper.scanner.Location;

import java.util.Optional;

import static java.lang.Double.parseDouble;

public class RealConstant implements NumericConstant<Double>, SimplifyingAddition {
	private final Location location;
	private final double value;

	private RealConstant(Location location, Double value) {
		this.location = location;
		this.value = value;
	}

	public static RealConstant realConstant(Location location, String value) {
		return realConstant(location, parseDouble(value));
	}

	public static RealConstant realConstant(Location location, double value) {
		return new RealConstant(location, value);
	}

	@Override
	public Double getValue() {
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
	public Expression addToLeft(RealConstant left) {
		return addRealToLeft(left);
	}

	@Override
	public Expression addToLeft(IntegerConstant left) {
		return addRealToLeft(left);
	}

	@Override
	public Expression addToLeft(StringConstant left) {
		return left.concatenateToRight(this);
	}

	public RealConstant addRealToLeft(NumericConstant<?> left) {
		return realConstant(locationBetween(left, this), left.getValue().doubleValue() + value);
	}

	public RealConstant addRealToRight(NumericConstant<?> right) {
		return realConstant(locationBetween(this, right), value + right.getValue().doubleValue());
	}
}
