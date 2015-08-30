package io.github.theangrydev.op.parser;

public interface BinaryOperator extends Expression {
	Expression getLeft();
	Expression getRight();
}
