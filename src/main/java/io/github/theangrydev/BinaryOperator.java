package io.github.theangrydev;

public interface BinaryOperator extends Expression {
	Expression getLeft();
	Expression getRight();
}
