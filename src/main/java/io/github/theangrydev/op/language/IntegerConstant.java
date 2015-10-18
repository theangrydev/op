package io.github.theangrydev.op.language;


import io.github.theangrydev.opper.scanner.Location;

import static java.lang.Integer.parseInt;

public class IntegerConstant implements NumericConstant<Integer> {
	private final Location location;
	private final Integer value;

	private IntegerConstant(Location location, Integer value) {
		this.location = location;
		this.value = value;
	}

	public static IntegerConstant of(Location location, String value) {
		return new IntegerConstant(location, parseInt(value));
	}

	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value.toString();
	}

	@Override
	public Location getLocation() {
		return location;
	}
}
