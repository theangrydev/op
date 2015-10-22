package io.github.theangrydev.op.language;

import java.util.Optional;

public interface NotSimplifyingAddition extends AdditionSimplifier {

	@Override
	default Optional<Expression> simplifyAddToRight(Expression right) {
		return Optional.empty();
	}

	@Override
	default Optional<Expression> simplifyAddToLeft(RealConstant left) {
		return Optional.empty();
	}

	@Override
	default Optional<Expression> simplifyAddToLeft(IntegerConstant left) {
		return Optional.empty();
	}

	@Override
	default Optional<Expression> simplifyAddToLeft(StringConstant left) {
		return Optional.empty();
	}
}
