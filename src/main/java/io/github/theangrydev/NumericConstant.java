package io.github.theangrydev;

public interface NumericConstant<T> extends Constant<T> {
	NumericConstant<T> minus(NumericConstant<T> other);
}