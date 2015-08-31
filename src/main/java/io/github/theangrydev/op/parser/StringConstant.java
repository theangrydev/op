package io.github.theangrydev.op.parser;

public class StringConstant implements Constant<String> {
	private final Location location;
	private final String value;

	private StringConstant(Location location, String value) {
		this.location = location;
		this.value = value;
	}

	public static StringConstant of(Location location, String value) {
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
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public Location getLocation() {
		return location;
	}
}
