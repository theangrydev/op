package io.github.theangrydev;

import org.junit.Test;

public class RealConstantTest implements WithAssertions {

	@Test
	public void shouldStoreAValue() throws Exception {
		assertThat(RealConstant.of(10.5)).hasValue(10.5);
	}

	@Test
	public void shouldAddAnotherConstant() throws Exception {
		RealConstant value1 = RealConstant.of(1.5);
		RealConstant value2 = RealConstant.of(2.5);
		assertThat(value1.add(value2)).hasValue(4.0);
		assertThat(value2.add(value1)).hasValue(4.0);
	}

	@Test
	public void shouldSubtractAnotherConstant() throws Exception {
		RealConstant value1 = RealConstant.of(1.5);
		RealConstant value2 = RealConstant.of(2.5);
		assertThat(value1.minus(value2)).hasValue(-1.0);
		assertThat(value2.minus(value1)).hasValue(1.0);
	}

	@Test
	public void shouldMultiplyByAnotherConstant() throws Exception {
		RealConstant value1 = RealConstant.of(-3.5);
		RealConstant value2 = RealConstant.of(2.0);
		assertThat(value1.times(value2)).hasValue(-7.0);
		assertThat(value2.times(value1)).hasValue(-7.0);
	}

	@Test
	public void shouldDivideByAnotherConstant() throws Exception {
		RealConstant value1 = RealConstant.of(10.0);
		RealConstant value2 = RealConstant.of(2.0);
		assertThat(value1.divide(value2)).hasValue(5.0);
		assertThat(value2.divide(value1)).hasValue(0.2);
	}

	@Test
	public void shouldToStringTheValue() throws Exception {
		assertThat(RealConstant.of(100.105)).hasToString("100.105");
	}

	@Test
	public void acceptShouldVisitTheRealConstant() throws Exception {
		RealConstant value = RealConstant.of(10.5);
		value.accept(new Expression.Visitor() {
			@Override
			public void visit(RealConstant realConstant) {
				assertThat(value).isSameAs(realConstant);
			}

			@Override
			public void visit(IntegerConstant realConstant) {
				fail("Should visit the RealConstant");
			}

			@Override
			public void visit(StringConstant stringConstant) {
				fail("Should visit the RealConstant");
			}

			@Override
			public void visit(TypeExpression typeExpression) {
				fail("Should visit the RealConstant");
			}

			@Override
			public void visit(IntegerAddition integerAddition) {
				fail("Should visit the RealConstant");
			}

			@Override
			public void visit(TypeAddition typeAddition) {
				fail("Should visit the RealConstant");
			}
		});
	}
}
