package io.github.theangrydev.op.parser;

public interface NumericConstant<T> extends Constant<T> {
	NumericConstant<T> add(NumericConstant<T> other);
	NumericConstant<T> minus(NumericConstant<T> other);
	NumericConstant<T> times(NumericConstant<T> other);
	NumericConstant<T> divide(NumericConstant<T> other);
}
