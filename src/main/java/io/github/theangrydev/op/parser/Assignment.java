package io.github.theangrydev.op.parser;

public interface Assignment extends Statement {
	TypeExpression getTargetType();
	Expression getExpression();
}
