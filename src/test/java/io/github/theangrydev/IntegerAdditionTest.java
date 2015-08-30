package io.github.theangrydev;

import org.junit.Test;

public class IntegerAdditionTest implements WithAssertions {

	@Test
	public void shouldStoreLeftAndRight() throws Exception {
		IntegerConstant left = IntegerConstant.of(100);
		IntegerConstant right = IntegerConstant.of(102);
		assertThat(IntegerAddition.add(left, right)).hasLeft(left).hasRight(right);
	}

	@Test
	public void shouldToStringTheLeftAndRight() throws Exception {
		IntegerConstant left = IntegerConstant.of(1);
		IntegerConstant right = IntegerConstant.of(12);
		assertThat(IntegerAddition.add(left, right)).hasToString("1+12");
	}

	@Test
	public void acceptShouldVisitTheIntegerAddition() throws Exception {
		IntegerConstant left = IntegerConstant.of(1);
		IntegerConstant right = IntegerConstant.of(12);
		IntegerAddition value = IntegerAddition.add(left, right);
		value.accept(new Expression.Visitor() {
			@Override
			public void visit(IntegerAddition integerAddition) {
				assertThat(value).isSameAs(integerAddition);
			}

			@Override
			public void visit(RealConstant realConstant) {
				fail("Should visit the IntegerAddition");
			}

			@Override
			public void visit(StringConstant stringConstant) {
				fail("Should visit the IntegerAddition");
			}

			@Override
			public void visit(TypeExpression typeExpression) {
				fail("Should visit the IntegerAddition");
			}

			@Override
			public void visit(IntegerConstant integerConstant) {
				fail("Should visit the IntegerAddition");
			}

			@Override
			public void visit(TypeAddition typeAddition) {
				fail("Should visit the IntegerAddition");
			}
		});
	}
}
