package io.github.theangrydev;

public interface Addition<T> extends Expression {
	Expression getLeft();
	Expression getRight();
}
