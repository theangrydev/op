package io.github.theangrydev.op.parser;

import io.github.theangrydev.opper.scanner.Location;

import static java.lang.Double.parseDouble;

public class RealConstant implements NumericConstant<Double> {
	private final Location location;
	private final Double value;

	private RealConstant(Location location, Double value) {
		this.location = location;
		this.value = value;
	}

	public static RealConstant of(Location location, String value) {
		return new RealConstant(location, parseDouble(value));
	}

	@Override
	public Double getValue() {
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
