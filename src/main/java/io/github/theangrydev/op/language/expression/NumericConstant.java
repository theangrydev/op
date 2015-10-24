package io.github.theangrydev.op.language.expression;

public interface NumericConstant<T extends Number> extends Constant<T> {
	boolean isZero();
}
