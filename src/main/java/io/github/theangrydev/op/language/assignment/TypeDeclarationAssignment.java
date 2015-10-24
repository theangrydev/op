package io.github.theangrydev.op.language.assignment;

import io.github.theangrydev.op.generation.ProgramCompiler;
import io.github.theangrydev.op.language.expression.Expression;
import io.github.theangrydev.op.language.expression.TypeExpression;
import io.github.theangrydev.opper.scanner.Location;

public class TypeDeclarationAssignment implements Assignment<TypeDeclarationAssignment> {
	private final Location location;
	private final Type existingType;
	private final TypeExpression targetType;
	private Expression expression;

	private TypeDeclarationAssignment(TypeExpression targetType, Type existingType, Expression expression) {
		this.location = locationBetween(targetType, existingType);
		this.existingType = existingType;
		this.targetType = targetType;
		this.expression = expression;
	}

	public static TypeDeclarationAssignment of(TypeDeclaration typeDeclaration, Expression expression) {
		return new TypeDeclarationAssignment(typeDeclaration.getTargetType(), typeDeclaration.getExistingType(), expression);
	}

	public Type getExistingType() {
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

	@Override
	public void checkTypes(ProgramCompiler programCompiler) {
		expression.checkTypes(programCompiler);
		existingType.checkTypes(programCompiler);
		programCompiler.registerVariableReference(targetType.getType(), existingType.underlyingType());
		targetType.checkTypes(programCompiler);
	}

	@Override
	public void compile(ProgramCompiler programCompiler) {
		expression.compile(programCompiler);
		targetType.typeReference().store(programCompiler);
	}

	@Override
	public TypeDeclarationAssignment simplify() {
		expression = expression.simplify();
		return this;
	}
}
