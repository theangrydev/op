package io.github.theangrydev.op.parser;

import io.github.theangrydev.WithAssertions;
import io.github.theangrydev.opper.scanner.Location;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class IntegerConstantTest implements WithAssertions {
	private final Location location = mock(Location.class);

	@Test
	public void shouldStoreAValue() throws Exception {
		assertThat(IntegerConstant.of(location, "100")).hasValue(100);
	}

	@Test
	public void shouldToStringTheValue() throws Exception {
		assertThat(IntegerConstant.of(location, "100")).hasToString("100");
	}

	@Test
	public void acceptShouldVisitTheIntegerConstant() throws Exception {
		IntegerConstant value = IntegerConstant.of(location, "100");
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

			@Override
			public void visit(TypeExpression typeExpression) {
				fail("Should visit the IntegerConstant");
			}

			@Override
			public void visit(Addition addition) {
				fail("Should visit the IntegerConstant");
			}
		});
	}
}
