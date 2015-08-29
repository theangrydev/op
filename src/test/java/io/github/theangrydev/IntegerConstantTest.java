package io.github.theangrydev;

import org.junit.Test;

public class IntegerConstantTest implements WithAssertions {

	@Test
	public void shouldStoreAValue() throws Exception {
		assertThat(IntegerConstant.of(100)).hasValue(100);
	}

	@Test
	public void shouldAddAnotherConstant() throws Exception {
		IntegerConstant value1 = IntegerConstant.of(1);
		IntegerConstant value2 = IntegerConstant.of(2);
		assertThat(value1.add(value2)).hasValue(3);
		assertThat(value2.add(value1)).hasValue(3);
	}

	@Test
	public void shouldToStringTheValue() throws Exception {
		assertThat(IntegerConstant.of(100)).hasToString("100");
	}

	@Test
	public void acceptShouldVisitTheIntegerConstant() throws Exception {
		IntegerConstant value = IntegerConstant.of(100);
		value.accept(new Expression.Visitor() {
			@Override
			public void visit(IntegerConstant integerConstant) {
				assertThat(value).isSameAs(integerConstant);
			}

			@Override
			public void visit(RealConstant realConstant) {
				fail("Should visit the IntegerConstant");
			}

			@Override
			public void visit(StringConstant stringConstant) {
				fail("Should visit the IntegerConstant");
			}
		});
	}
}
