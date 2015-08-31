package io.github.theangrydev.op.parser;

import io.github.theangrydev.WithAssertions;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class TypeExpressionTest implements WithAssertions {
	private final Location location = mock(Location.class);

	@Test
	public void shouldStoreTheValue() throws Exception {
		assertThat(TypeExpression.of(location, "ABC")).hasType("ABC");
	}

	@Test
	public void shouldToStringTheValue() throws Exception {
		assertThat(TypeExpression.of(location, "ABC")).hasToString("ABC");
	}

	@Test
	public void acceptShouldVisitTheTypeDeclaration() throws Exception {
		TypeExpression value = TypeExpression.of(location, "target");
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
