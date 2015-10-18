package io.github.theangrydev.op.language;

import io.github.theangrydev.opper.scanner.Location;

public class TypeDeclaration implements ProgramElement<TypeDeclaration> {

	private final Location location;
	private final TypeExpression targetType;
	private final TypeExpression existingType;

	private TypeDeclaration(TypeExpression targetType, TypeExpression existingType) {
		this.location = locationBetween(targetType, existingType);
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

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public TypeDeclaration simplify() {
		return this;
	}
}
