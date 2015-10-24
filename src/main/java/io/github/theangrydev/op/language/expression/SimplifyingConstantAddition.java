package io.github.theangrydev.op.language.expression;

import java.util.Optional;

public interface SimplifyingConstantAddition extends AdditionSimplifier {
	default Optional<Expression> simplifyAddToLeft(RealConstant left) {
		return Optional.of(addToLeft(left));
	}

	default Optional<Expression> simplifyAddToLeft(IntegerConstant left) {
		return Optional.of(addToLeft(left));
	}

	default Optional<Expression> simplifyAddToLeft(StringConstant left) {
		return Optional.of(addToLeft(left));
	}

	Expression addToLeft(RealConstant left);
	Expression addToLeft(IntegerConstant left);
	Expression addToLeft(StringConstant left);
}