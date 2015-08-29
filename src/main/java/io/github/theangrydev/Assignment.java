package io.github.theangrydev;

public class Assignment implements Statement {

	private final String targetType;
	private final Expression expression;

	private Assignment(String targetType, Expression expression) {
		this.targetType = targetType;
		this.expression = expression;
		System.out.println(this);
	}

	public static Assignment of(String targetType, Expression expression) {
		return new Assignment(targetType, expression);
	}

	@Override
	public String toString() {
		return targetType + "=" + expression;
	}
}
