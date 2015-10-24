package io.github.theangrydev.op.language.expression;

import java.util.Optional;

public interface AdditionSimplifier {
	default Optional<Expression> simplifyAddToRight(Expression right) {
		return Optional.empty();
	}

	default Optional<Expression> simplifyAddToLeft(TypeExpression left) {
		return Optional.empty();
	}

	default Optional<Expression> simplifyAddToLeft(RealConstant left) {
		return Optional.empty();
	}

	default Optional<Expression> simplifyAddToLeft(IntegerConstant left) {
		return Optional.empty();
	}

	default Optional<Expression> simplifyAddToLeft(StringConstant left) {
		return Optional.empty();
	}
}
