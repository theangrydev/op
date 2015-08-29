package io.github.theangrydev;

public class TypeDeclaration {

	private final String targetType;
	private final String existingType;

	private TypeDeclaration(String targetType, String existingType) {
		this.targetType = targetType;
		this.existingType = existingType;
	}

	public static TypeDeclaration of(String targetType, String existingType) {
		return new TypeDeclaration(targetType, existingType);
	}

	public String getTargetType() {
		return targetType;
	}

	public String getExistingType() {
		return existingType;
	}

	@Override
	public String toString() {
		return targetType + ":" + existingType;
	}
}
