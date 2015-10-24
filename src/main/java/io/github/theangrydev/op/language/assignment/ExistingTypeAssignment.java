package io.github.theangrydev.op.language.assignment;


import com.google.common.base.Preconditions;
import io.github.theangrydev.op.generation.ProgramCompiler;
import io.github.theangrydev.op.generation.TypeReference;
import io.github.theangrydev.op.language.expression.Expression;
import io.github.theangrydev.op.language.expression.TypeExpression;
import io.github.theangrydev.opper.scanner.Location;

public class ExistingTypeAssignment implements Assignment<ExistingTypeAssignment> {
	private final Location location;
	private final TypeExpression targetType;
	private Expression expression;

	private ExistingTypeAssignment(TypeExpression targetType, Expression expression) {
		this.location = locationBetween(targetType, expression);
		this.targetType = targetType;
		this.expression = expression;
	}

	public static ExistingTypeAssignment of(TypeExpression targetType, Expression expression) {
		return new ExistingTypeAssignment(targetType, expression);
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
		return targetType + "=" + expression;
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public ExistingTypeAssignment simplify() {
		expression = expression.simplify();
		return this;
	}

	@Override
	public void checkTypes(ProgramCompiler programCompiler) {
		expression.checkTypes(programCompiler);
		targetType.checkTypes(programCompiler);
		TypeReference targetTypeReference = targetType.typeReference();
		TypeReference expressionTypeReference = expression.typeReference();
		Preconditions.checkState(targetTypeReference == expressionTypeReference, "Target type '%s' should match expression type '%s'", targetTypeReference, expressionTypeReference);
	}

	@Override
	public void compile(ProgramCompiler programCompiler) {
		expression.compile(programCompiler);
		targetType.underlyingType().store(programCompiler, targetType.typeReference());
	}
}
