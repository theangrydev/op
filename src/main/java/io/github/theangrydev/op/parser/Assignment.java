package io.github.theangrydev.op.parser;

public interface Assignment extends Statement {
	String getTargetType();
	Expression getExpression();
}
