package io.github.theangrydev.op.language.assignment;

import io.github.theangrydev.op.language.Statement;
import io.github.theangrydev.op.language.expression.Expression;
import io.github.theangrydev.op.language.expression.TypeExpression;

public interface Assignment<T extends Assignment<T>> extends Statement<T> {
	TypeExpression getTargetType();
	Expression getExpression();
}
