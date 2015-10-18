package io.github.theangrydev.op.language;


import io.github.theangrydev.opper.scanner.Location;

import java.util.Optional;

import static io.github.theangrydev.op.language.RealConstant.realConstant;
import static io.github.theangrydev.op.language.StringConstant.stringConstant;
import static java.lang.Integer.parseInt;
import static java.util.Optional.of;

public class IntegerConstant implements NumericConstant<Integer> {
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
	public Optional<Expression> addToRight(Expression right) {
		return right.addToLeft(this);
	}

	@Override
	public Optional<Expression> addToLeft(RealConstant left) {
		return of(realConstant(ProgramElement.locationBetween(left, this), value + left.getValue()));
	}

	@Override
	public Optional<Expression> addToLeft(IntegerConstant left) {
		return of(integerConstant(ProgramElement.locationBetween(left, this), value + left.getValue()));
	}

	@Override
	public Optional<Expression> addToLeft(StringConstant left) {
		return of(stringConstant(ProgramElement.locationBetween(left, this), left.getValue() + value));
	}
}
