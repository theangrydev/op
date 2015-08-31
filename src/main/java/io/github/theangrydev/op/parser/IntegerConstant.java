package io.github.theangrydev.op.parser;


public class IntegerConstant implements NumericConstant<Integer> {
	private final Location location;
	private final Integer value;

	private IntegerConstant(Location location, Integer value) {
		this.location = location;
		this.value = value;
	}

	public static IntegerConstant of(Location location, Integer value) {
		return new IntegerConstant(location, value);
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
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public Location getLocation() {
		return location;
	}
}
