package io.github.theangrydev;

public class Constant<T> implements Expression {

	private final T value;

	private Constant(T value) {
		this.value = value;
		System.out.println(this);
	}

	public static Constant<Integer> integer(Integer integer) {
		return new Constant<>(integer);
	}

	public static Constant<String> string(String string) {
		return new Constant<>(string);
	}

	public static Constant<Double> real(Double real) {
		return new Constant<>(real);
	}

	public T value() {
		return value;
	}

	@Override
	public String toString() {
		return value.toString();
	}
}
