package io.github.theangrydev.op.language;

import io.github.theangrydev.opper.scanner.Location;

import java.util.Optional;

import static io.github.theangrydev.op.language.StringConstant.stringConstant;
import static java.lang.Double.parseDouble;

public class RealConstant implements NumericConstant<Double> {
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
	public Optional<Expression> addToRight(Expression right) {
		return right.addToLeft(this);
	}

	@Override
	public Optional<Expression> addToLeft(RealConstant left) {
		return Optional.of(realConstant(locationBetween(left, this), left.value + value));
	}

	@Override
	public Optional<Expression> addToLeft(IntegerConstant left) {
		return Optional.of(realConstant(locationBetween(left, this), left.getValue() + value));
	}

	@Override
	public Optional<Expression> addToLeft(StringConstant left) {
		return Optional.of(stringConstant(locationBetween(left, this), left.getValue() + value));
	}
}
