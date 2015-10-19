package io.github.theangrydev.op.language;

public interface BinaryOperator extends Expression, NotSimplifyingAddition {
	Expression getLeft();
	Expression getRight();
}
