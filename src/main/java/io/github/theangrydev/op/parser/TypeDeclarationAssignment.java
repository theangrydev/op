package io.github.theangrydev.op.parser;

import io.github.theangrydev.opper.scanner.Location;

public class TypeDeclarationAssignment implements Assignment {
	private final TypeExpression existingType;
	private final TypeExpression targetType;
	private final Expression expression;

	private TypeDeclarationAssignment(TypeExpression existingType, TypeExpression targetType, Expression expression) {
		this.existingType = existingType;
		this.targetType = targetType;
		this.expression = expression;
	}

	public static TypeDeclarationAssignment of(TypeDeclaration typeDeclaration, Expression expression) {
		return new TypeDeclarationAssignment(typeDeclaration.getExistingType(), typeDeclaration.getTargetType(), expression);
	}

	public TypeExpression getExistingType() {
		return existingType;
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
		return targetType + ":" + existingType + "=" + expression;
	}

	@Override
	public Location getLocation() {
		return null;
//		return Location.between(targetType, expression);
	}
}
