package io.github.theangrydev.op.parser;

public class StringConstant implements Constant<String> {
	private final String value;

	private StringConstant(String value) {
		this.value = value;
	}

	public static StringConstant of(String value) {
		return new StringConstant(value);
	}

	public StringConstant concat(StringConstant other) {
		return of(value + other.getValue());
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
}
