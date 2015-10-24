package io.github.theangrydev.op.language.assignment;

import io.github.theangrydev.op.generation.ProgramCompiler;
import io.github.theangrydev.op.language.ProgramElement;
import io.github.theangrydev.op.language.expression.TypeExpression;
import io.github.theangrydev.opper.scanner.Location;

public class TypeDeclaration implements ProgramElement<TypeDeclaration> {

	private final Location location;
	private final TypeExpression targetType;
	private final Type existingType;

	private TypeDeclaration(TypeExpression targetType, Type existingType) {
		this.location = locationBetween(targetType, existingType);
		this.targetType = targetType;
		this.existingType = existingType;
	}

	public static TypeDeclaration of(TypeExpression targetType, Type existingType) {
		return new TypeDeclaration(targetType, existingType);
	}

	public TypeExpression getTargetType() {
		return targetType;
	}

	public Type getExistingType() {
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

	@Override
	public void checkTypes(ProgramCompiler programCompiler) {
		existingType.checkTypes(programCompiler);
		programCompiler.registerVariableReference(targetType.getType(), existingType.underlyingType());
		targetType.checkTypes(programCompiler);
	}

	@Override
	public void compile(ProgramCompiler programCompiler) {
		//TODO: compile default expression here for the underlying type
		targetType.typeReference().store(programCompiler);
	}
}
