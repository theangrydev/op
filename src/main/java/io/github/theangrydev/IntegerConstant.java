package io.github.theangrydev;

public class IntegerConstant implements Constant<Integer> {
	private final Integer value;

	private IntegerConstant(Integer value) {
		this.value = value;
		System.out.println(this);
	}

	public static IntegerConstant of(Integer value) {
		return new IntegerConstant(value);
	}

	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value.toString();
	}
}
