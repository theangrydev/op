package io.github.theangrydev.op.language;

import io.github.theangrydev.op.generation.TypeReference;
import io.github.theangrydev.op.generation.UnderlyingType;

public interface Expression extends ProgramElement<Expression>, AdditionSimplifier {
	TypeReference typeReference();
	default UnderlyingType underlyingType() {
		return typeReference().underlyingType();
	}
}
