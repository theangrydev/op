package io.github.theangrydev;

public interface Addition<T> extends Expression<T> {
	Expression<T> getLeft();
	Expression<T> getRight();
}
