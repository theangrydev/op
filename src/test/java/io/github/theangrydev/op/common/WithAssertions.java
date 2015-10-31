package io.github.theangrydev.op.common;

import io.github.theangrydev.op.language.assignment.*;
import io.github.theangrydev.op.language.expression.*;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.StrictAssertions;
import org.assertj.core.api.ThrowableAssert;

public interface WithAssertions extends org.assertj.core.api.WithAssertions {

	default AbstractThrowableAssert<?, ? extends Throwable> assertThatThrownBy(ThrowableAssert.ThrowingCallable shouldRaiseThrowable) {
		return StrictAssertions.assertThatThrownBy(shouldRaiseThrowable);
	}

	default ExistingTypeAssignmentAssert assertThat(ExistingTypeAssignment actual) {
		return new ExistingTypeAssignmentAssert(actual);
	}

	default TypeDeclarationAssignmentAssert assertThat(TypeDeclarationAssignment actual) {
		return new TypeDeclarationAssignmentAssert(actual);
	}

	default ConstantAssert assertThat(Constant<?> actual) {
		return new ConstantAssert(actual);
	}

	default IntegerConstantAssert assertThat(IntegerConstant actual) {
		return new IntegerConstantAssert(actual);
	}

	default AssignmentAssert assertThat(Assignment actual) {
		return new AssignmentAssert(actual);
	}

	default TypeAssert assertThat(Type type) {
		return new TypeAssert(type);
	}

	default TypeExpressionAssert assertThat(TypeExpression actual) {
		return new TypeExpressionAssert(actual);
	}

	default TypeDeclarationAssert assertThat(TypeDeclaration actual) {
		return new TypeDeclarationAssert(actual);
	}

	default AdditionAssert assertThat(Addition actual) {
		return new AdditionAssert(actual);
	}
}
