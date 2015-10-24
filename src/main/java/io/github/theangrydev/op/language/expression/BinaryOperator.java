package io.github.theangrydev.op.language.expression;

public interface BinaryOperator extends Expression, AdditionSimplifier {
	Expression getLeft();
	Expression getRight();
}
