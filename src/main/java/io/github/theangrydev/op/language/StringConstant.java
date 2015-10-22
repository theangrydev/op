package io.github.theangrydev.op.language;

import io.github.theangrydev.opper.scanner.Location;

import java.util.Optional;

public class StringConstant implements Constant<String>, SimplifyingAddition {
	private final Location location;
	private final String value;

	private StringConstant(Location location, String value) {
		this.location = location;
		this.value = value;
	}

	public static StringConstant quotedStringConstant(Location location, String value) {
		return stringConstant(location, value.substring(1, value.length() - 1));
	}

	public static StringConstant stringConstant(Location location, String value) {
		return new StringConstant(location, value);
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
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
		return concatenateToLeft(left);
	}

	@Override
	public Expression addToLeft(IntegerConstant left) {
		return concatenateToLeft(left);
	}

	@Override
	public Expression addToLeft(StringConstant left) {
		return concatenateToLeft(left);
	}

	public Expression concatenateToLeft(Constant<?> left) {
		return stringConstant(locationBetween(left, this), left.toString() + value);
	}

	public Expression concatenateToRight(Constant<?> right) {
		return stringConstant(locationBetween(this, right), value + right.toString());
	}
}
