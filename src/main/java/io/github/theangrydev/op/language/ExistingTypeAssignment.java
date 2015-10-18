package io.github.theangrydev.op.language;


import io.github.theangrydev.opper.scanner.Location;

public class ExistingTypeAssignment implements Assignment<ExistingTypeAssignment> {
	private final Location location;
	private final TypeExpression targetType;
	private Expression expression;

	private ExistingTypeAssignment(TypeExpression targetType, Expression expression) {
		this.location = locationBetween(targetType, expression);
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
	public Location getLocation() {
		return location;
	}

	@Override
	public ExistingTypeAssignment simplify() {
		expression = expression.simplify();
		return this;
	}
}
