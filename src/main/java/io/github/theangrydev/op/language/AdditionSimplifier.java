package io.github.theangrydev.op.language;

import java.util.Optional;

public interface AdditionSimplifier {
	Optional<Expression> simplifyAddToRight(Expression right);
	Optional<Expression> simplifyAddToLeft(RealConstant left);
	Optional<Expression> simplifyAddToLeft(IntegerConstant left);
	Optional<Expression> simplifyAddToLeft(StringConstant left);
}
