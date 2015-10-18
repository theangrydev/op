package io.github.theangrydev.op.parser;


import io.github.theangrydev.opper.scanner.Location;

public class ExistingTypeAssignment implements Assignment {
	private final TypeExpression targetType;
	private final Expression expression;

	private ExistingTypeAssignment(TypeExpression targetType, Expression expression) {
		this.targetType = targetType;
		this.expression = expression;
	}

	public static ExistingTypeAssignment of(TypeExpression targetType, Expression expression) {
		return new ExistingTypeAssignment(targetType, expression);
	}

	@Override
	public TypeExpression getTargetType() {
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

	@Override
	public Location location() {
		return Location.between(targetType.location(), expression.location());
	}
}
