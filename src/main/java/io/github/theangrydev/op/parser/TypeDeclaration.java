package io.github.theangrydev.op.parser;

public class TypeDeclaration {

	private final TypeExpression targetType;
	private final TypeExpression existingType;

	private TypeDeclaration(TypeExpression targetType, TypeExpression existingType) {
		this.targetType = targetType;
		this.existingType = existingType;
	}

	public static TypeDeclaration of(TypeExpression targetType, TypeExpression existingType) {
		return new TypeDeclaration(targetType, existingType);
	}

	public TypeExpression getTargetType() {
		return targetType;
	}

	public TypeExpression getExistingType() {
		return existingType;
	}

	@Override
	public String toString() {
		return targetType + ":" + existingType;
	}
}
