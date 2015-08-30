package io.github.theangrydev;

public class TypeExpression<T> implements Expression<T> {

	private final String type;

	private TypeExpression(String type) {
		this.type = type;
	}

	public static <T> TypeExpression<T> of(String type) {
		return new TypeExpression<>(type);
	}

	public String getType() {
		return type;
	}

	@Override
	public void accept(Visitor<T> visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return type;
	}
}
