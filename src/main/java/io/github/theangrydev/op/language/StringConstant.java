package io.github.theangrydev.op.language;

import io.github.theangrydev.opper.scanner.Location;

import java.util.Optional;

public class StringConstant implements Constant<String> {
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
	public Optional<Expression> addToRight(Expression right) {
		return right.addToLeft(this);
	}

	@Override
	public Optional<Expression> addToLeft(RealConstant left) {
		return concatenate(left);
	}

	@Override
	public Optional<Expression> addToLeft(IntegerConstant left) {
		return concatenate(left);
	}

	@Override
	public Optional<Expression> addToLeft(StringConstant left) {
		return concatenate(left);
	}

	private Optional<Expression> concatenate(Constant<?> left) {
		return Optional.of(stringConstant(ProgramElement.locationBetween(left, this), left.toString() + value));
	}
}
