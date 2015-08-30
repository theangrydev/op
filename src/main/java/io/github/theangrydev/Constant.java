package io.github.theangrydev;

public interface Constant<T> extends Expression<T> {
	T getValue();
	Constant<T> add(Constant<T> other);
}
