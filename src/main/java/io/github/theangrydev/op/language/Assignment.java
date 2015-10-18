package io.github.theangrydev.op.language;

public interface Assignment<T extends Assignment<T>> extends Statement<T> {
	TypeExpression getTargetType();
	Expression getExpression();
}
