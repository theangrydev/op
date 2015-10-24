package io.github.theangrydev.op.language.expression;

import io.github.theangrydev.op.generation.TypeReference;
import io.github.theangrydev.op.generation.UnderlyingType;
import io.github.theangrydev.op.language.ProgramElement;

public interface Expression extends ProgramElement<Expression>, AdditionSimplifier {
	TypeReference typeReference();
	default UnderlyingType underlyingType() {
		return typeReference().underlyingType();
	}
}
