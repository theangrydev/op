package io.github.theangrydev.op.language.expression;

public interface Constant<T> extends Expression {
	T getValue();
}
