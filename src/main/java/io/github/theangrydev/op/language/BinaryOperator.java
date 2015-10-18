package io.github.theangrydev.op.language;

public interface BinaryOperator extends Expression {
	Expression getLeft();
	Expression getRight();
}
