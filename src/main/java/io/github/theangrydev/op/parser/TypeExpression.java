package io.github.theangrydev.op.parser;

public class TypeExpression implements Expression {

	private final String type;

	private TypeExpression(String type) {
		this.type = type;
	}

	public static TypeExpression of(String type) {
		return new TypeExpression(type);
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
}
