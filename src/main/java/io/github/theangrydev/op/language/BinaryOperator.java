package io.github.theangrydev.op.language;

public interface BinaryOperator extends Expression, AdditionSimplifier {
	Expression getLeft();
	Expression getRight();
}
