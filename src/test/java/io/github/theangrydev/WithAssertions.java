package io.github.theangrydev;

import java_cup.runtime.Symbol;
import java_cup.runtime.SymbolAssert;

public interface WithAssertions extends org.assertj.core.api.WithAssertions {

	default SymbolAssert assertThat(Symbol actual) {
		return new SymbolAssert(actual);
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
