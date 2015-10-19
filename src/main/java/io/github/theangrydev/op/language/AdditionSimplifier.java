package io.github.theangrydev.op.language;

import java.util.Optional;

public interface AdditionSimplifier {
	Optional<Expression> addToRight(Expression right);
	Optional<Expression> addToLeft(RealConstant left);
	Optional<Expression> addToLeft(IntegerConstant left);
	Optional<Expression> addToLeft(StringConstant left);
}
