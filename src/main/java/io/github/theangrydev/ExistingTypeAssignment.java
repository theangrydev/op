package io.github.theangrydev;

public class ExistingTypeAssignment implements Assignment {
	private final String targetType;
	private final Expression expression;

	private ExistingTypeAssignment(String targetType, Expression expression) {
		this.targetType = targetType;
		this.expression = expression;
	}

	public static ExistingTypeAssignment of(String targetType, Expression expression) {
		return new ExistingTypeAssignment(targetType, expression);
	}

	@Override
	public String getTargetType() {
		return targetType;
	}

	@Override
	public Expression getExpression() {
		return expression;
	}

	@Override
	public String toString() {
		return targetType + "=" + expression;
	}
}
