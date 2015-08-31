package io.github.theangrydev.op.parser;

import io.github.theangrydev.WithAssertions;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class StringConstantTest implements WithAssertions {
	private final Location location = mock(Location.class);

	@Test
	public void shouldStoreAValue() throws Exception {
		assertThat(StringConstant.of(location, "100")).hasValue("100");
	}

	@Test
	public void shouldToStringTheValue() throws Exception {
		assertThat(StringConstant.of(location, "100")).hasToString("100");
	}

	@Test
	public void acceptShouldVisitTheStringConstant() throws Exception {
		StringConstant value = StringConstant.of(location, "100");
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
