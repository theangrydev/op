package io.github.theangrydev;

public interface Assignment extends Statement {
	String getTargetType();
	Expression getExpression();
}
