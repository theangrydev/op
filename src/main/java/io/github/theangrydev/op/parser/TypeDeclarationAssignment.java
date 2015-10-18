package io.github.theangrydev.op.parser;

import io.github.theangrydev.opper.scanner.Location;

public class TypeDeclarationAssignment implements Assignment {
	private final Location location;
	private final TypeExpression existingType;
	private final TypeExpression targetType;
	private final Expression expression;

	private TypeDeclarationAssignment(Location location, TypeExpression existingType, TypeExpression targetType, Expression expression) {
		this.location = location;
		this.existingType = existingType;
		this.targetType = targetType;
		this.expression = expression;
	}

	public static TypeDeclarationAssignment of(TypeDeclaration typeDeclaration, Expression expression) {
		Location location = ProgramElement.locationBetween(typeDeclaration, expression);
		return new TypeDeclarationAssignment(location, typeDeclaration.getExistingType(), typeDeclaration.getTargetType(), expression);
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
		return location;
	}
}
