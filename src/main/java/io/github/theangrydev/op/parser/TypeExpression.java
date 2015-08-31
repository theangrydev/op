package io.github.theangrydev.op.parser;

public class TypeExpression implements Expression {
	private final Location location;
	private final String type;

	private TypeExpression(Location location, String type) {
		this.location = location;
		this.type = type;
	}

	public static TypeExpression of(Location leftLocation, String type) {
		return new TypeExpression(leftLocation, type);
	}

	public String getType() {
		return type;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return type;
	}

	@Override
	public Location getLocation() {
		return location;
	}
}
