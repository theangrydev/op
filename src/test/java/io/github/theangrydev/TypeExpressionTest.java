package io.github.theangrydev;

import org.junit.Test;

public class TypeExpressionTest implements WithAssertions {

	@Test
	public void shouldStoreTheValue() throws Exception {
		assertThat(TypeExpression.of("ABC")).hasType("ABC");
	}

	@Test
	public void shouldToStringTheValue() throws Exception {
		assertThat(TypeExpression.of("ABC")).hasToString("ABC");
	}

	@Test
	public void acceptShouldVisitTheTypeDeclaration() throws Exception {
		TypeExpression value = TypeExpression.of("target");
		value.accept(new Expression.Visitor() {
			@Override
			public void visit(TypeExpression typeExpression) {
				assertThat(value).isSameAs(typeExpression);
			}

			@Override
			public void visit(IntegerConstant integerConstant) {
				fail("Should visit the TypeExpression");
			}

			@Override
			public void visit(RealConstant realConstant) {
				fail("Should visit the TypeExpression");
			}

			@Override
			public void visit(StringConstant stringConstant) {
				fail("Should visit the TypeExpression");
			}

			@Override
			public void visit(Addition addition) {
				fail("Should visit the TypeExpression");
			}
		});
	}
}
