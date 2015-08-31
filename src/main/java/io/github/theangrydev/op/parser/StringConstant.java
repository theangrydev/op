package io.github.theangrydev.op.parser;

public class StringConstant implements Constant<String> {
	private final Location leftLocation;
	private final String value;

	private StringConstant(Location leftLocation, String value) {
		this.leftLocation = leftLocation;
		this.value = value;
	}

	public static StringConstant of(Location leftLocation, String value) {
		return new StringConstant(leftLocation, value);
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
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public Location getLocation() {
		return leftLocation;
	}
}
