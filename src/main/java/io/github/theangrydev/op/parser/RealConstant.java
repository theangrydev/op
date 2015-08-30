package io.github.theangrydev.op.parser;

public class RealConstant implements NumericConstant<Double> {
	private final Double value;

	private RealConstant(Double value) {
		this.value = value;
	}

	public static RealConstant of(Double value) {
		return new RealConstant(value);
	}

	@Override
	public Double getValue() {
		return value;
	}

	@Override
	public NumericConstant<Double> add(NumericConstant<Double> other) {
		return of(value + other.getValue());
	}

	@Override
	public NumericConstant<Double> minus(NumericConstant<Double> other) {
		return of(value - other.getValue());
	}

	@Override
	public NumericConstant<Double> times(NumericConstant<Double> other) {
		return of(value * other.getValue());
	}

	@Override
	public NumericConstant<Double> divide(NumericConstant<Double> other) {
		return of(value / other.getValue());
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
