package io.github.theangrydev;

public class IntegerConstant implements NumericConstant<Integer> {
	private final Integer value;

	private IntegerConstant(Integer value) {
		this.value = value;
	}

	public static IntegerConstant of(Integer value) {
		return new IntegerConstant(value);
	}

	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public Constant<Integer> add(Constant<Integer> other) {
		return of(value + other.getValue());
	}

	@Override
	public NumericConstant<Integer> minus(NumericConstant<Integer> other) {
		return of(value - other.getValue());
	}

	@Override
	public String toString() {
		return value.toString();
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
