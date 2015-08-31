package io.github.theangrydev.op.parser;

public class RealConstant implements NumericConstant<Double> {
	private final Location location;
	private final Double value;

	private RealConstant(Location location, Double value) {
		this.location = location;
		this.value = value;
	}

	public static RealConstant of(Location location, Double value) {
		return new RealConstant(location, value);
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
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public Location getLocation() {
		return location;
	}
}
