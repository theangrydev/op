package io.github.theangrydev.op.language;

import java.util.Optional;

public interface NotSimplifyingAddition extends AdditionSimplifier {

	@Override
	default Optional<Expression> addToRight(Expression right) {
		return Optional.empty();
	}

	@Override
	default Optional<Expression> addToLeft(RealConstant left) {
		return Optional.empty();
	}

	@Override
	default Optional<Expression> addToLeft(IntegerConstant left) {
		return Optional.empty();
	}

	@Override
	default Optional<Expression> addToLeft(StringConstant left) {
		return Optional.empty();
	}
}
