package io.github.theangrydev.op.language;

public interface Assignment extends Statement {
	TypeExpression getTargetType();
	Expression getExpression();
}
