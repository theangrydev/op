package io.github.theangrydev.op.parser;

public class TypeDeclarationAssignment implements Assignment {

	private final String existingType;
	private final String targetType;
	private final Expression expression;

	private TypeDeclarationAssignment(String existingType, String targetType, Expression expression) {
		this.existingType = existingType;
		this.targetType = targetType;
		this.expression = expression;
	}

	public static TypeDeclarationAssignment of(TypeDeclaration typeDeclaration, Expression expression) {
		return new TypeDeclarationAssignment(typeDeclaration.getExistingType(), typeDeclaration.getTargetType(), expression);
	}

	public String getExistingType() {
		return existingType;
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
		return targetType + ":" + existingType + "=" + expression;
	}
}
