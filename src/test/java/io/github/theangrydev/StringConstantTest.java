package io.github.theangrydev;

import org.junit.Test;

public class StringConstantTest implements WithAssertions {

	@Test
	public void shouldStoreAValue() throws Exception {
		assertThat(StringConstant.of("100")).hasValue("100");
	}

	@Test
	public void shouldConcatAnotherConstant() throws Exception {
		StringConstant value1 = StringConstant.of("1");
		StringConstant value2 = StringConstant.of("2");
		assertThat(value1.concat(value2)).hasValue("12");
		assertThat(value2.concat(value1)).hasValue("21");
	}

	@Test
	public void shouldToStringTheValue() throws Exception {
		assertThat(StringConstant.of("100")).hasToString("100");
	}

	@Test
	public void acceptShouldVisitTheStringConstant() throws Exception {
		StringConstant value = StringConstant.of("100");
		value.accept(new Expression.Visitor() {
			@Override
			public void visit(StringConstant stringConstant) {
				assertThat(value).isSameAs(stringConstant);
			}

			@Override
			public void visit(TypeExpression typeExpression) {
				fail("Should visit the StringConstant");
			}

			@Override
			public void visit(Addition addition) {
				fail("Should visit the StringConstant");
			}

			@Override
			public void visit(RealConstant realConstant) {
				fail("Should visit the StringConstant");
			}

			@Override
			public void visit(IntegerConstant stringConstant) {
				fail("Should visit the StringConstant");
			}
		});
	}
}
