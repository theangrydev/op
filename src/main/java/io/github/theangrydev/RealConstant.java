package io.github.theangrydev;

public class RealConstant implements Constant<Double> {
	private final Double value;

	private RealConstant(Double value) {
		this.value = value;
		System.out.println(this);
	}

	public static RealConstant of(Double value) {
		return new RealConstant(value);
	}

	public Double getValue() {
		return value;
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
