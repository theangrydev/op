package io.github.theangrydev;

public class StringConstant implements Constant<String> {
	private final String value;

	private StringConstant(String value) {
		this.value = value;
		System.out.println(this);
	}

	public static StringConstant of(String value) {
		return new StringConstant(value);
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}
}
