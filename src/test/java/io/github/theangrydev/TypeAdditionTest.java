package io.github.theangrydev;

import org.junit.Test;

public class TypeAdditionTest implements WithAssertions {

	@Test
	public void shouldStoreLeftAndRight() throws Exception {
		TypeExpression left = TypeExpression.of("A");
		TypeExpression right = TypeExpression.of("B");
		assertThat(TypeAddition.add(left, right)).hasLeft(left).hasRight(right);
	}

	@Test
	public void shouldToStringTheValue() throws Exception {
		TypeExpression left = TypeExpression.of("A");
		TypeExpression right = TypeExpression.of("B");
		assertThat(TypeAddition.add(left, right)).hasToString("A+B");
	}

	@Test
	public void acceptShouldVisitTheTypeAddition() throws Exception {
		TypeExpression left = TypeExpression.of("A");
		TypeExpression right = TypeExpression.of("B");
		TypeAddition value = TypeAddition.add(left, right);
		value.accept(new Expression.Visitor() {

			@Override
			public void visit(TypeAddition typeAddition) {
				assertThat(value).isSameAs(typeAddition);
			}

			@Override
			public void visit(IntegerAddition integerAddition) {
				fail("Should visit the TypeAddition");
			}

			@Override
			public void visit(RealConstant realConstant) {
				fail("Should visit the TypeAddition");
			}

			@Override
			public void visit(StringConstant stringConstant) {
				fail("Should visit the TypeAddition");
			}

			@Override
			public void visit(IntegerConstant integerConstant) {
				fail("Should visit the TypeAddition");
			}

			@Override
			public void visit(TypeExpression typeExpression) {
				fail("Should visit the TypeAddition");
			}
		});
	}
}
