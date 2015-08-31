package io.github.theangrydev.op.parser;

import io.github.theangrydev.WithAssertions;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class RealConstantTest implements WithAssertions {
	private final Location location = mock(Location.class);

	@Test
	public void shouldStoreAValue() throws Exception {
		assertThat(RealConstant.of(location, 10.5)).hasValue(10.5);
	}

	@Test
	public void shouldToStringTheValue() throws Exception {
		assertThat(RealConstant.of(location, 100.105)).hasToString("100.105");
	}

	@Test
	public void acceptShouldVisitTheRealConstant() throws Exception {
		RealConstant value = RealConstant.of(location, 10.5);
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
			public void visit(Addition addition) {
				fail("Should visit the RealConstant");
			}
		});
	}
}
