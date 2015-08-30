package io.github.theangrydev;

public class StringConstant implements Constant<String> {
	private final String value;

	private StringConstant(String value) {
		this.value = value;
	}

	public static StringConstant of(String value) {
		return new StringConstant(value);
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public Constant<String> add(Constant<String> other) {
		return of(value + other.getValue());
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public void accept(Visitor<String> visitor) {
		visitor.visit(this);
	}
}
