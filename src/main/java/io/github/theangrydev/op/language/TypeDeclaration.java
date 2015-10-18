package io.github.theangrydev.op.language;

import io.github.theangrydev.opper.scanner.Location;

public class TypeDeclaration implements ProgramElement {

	private final Location location;
	private final TypeExpression targetType;
	private final TypeExpression existingType;

	private TypeDeclaration(Location location, TypeExpression targetType, TypeExpression existingType) {
		this.location = location;
		this.targetType = targetType;
		this.existingType = existingType;
	}

	public static TypeDeclaration of(TypeExpression targetType, TypeExpression existingType) {
		Location location = ProgramElement.locationBetween(targetType, existingType);
		return new TypeDeclaration(location, targetType, existingType);
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
}
