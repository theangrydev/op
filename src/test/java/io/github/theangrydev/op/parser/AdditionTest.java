package io.github.theangrydev.op.parser;

import io.github.theangrydev.WithAssertions;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class AdditionTest implements WithAssertions {
	private final Location location = mock(Location.class);

	@Test
	public void shouldStoreLeftAndRight() throws Exception {
		TypeExpression left = TypeExpression.of(location, "A");
		TypeExpression right = TypeExpression.of(location, "B");
		assertThat(Addition.add(left, right)).hasLeft(left).hasRight(right);
	}

	@Test
	public void shouldToStringTheValue() throws Exception {
		TypeExpression left = TypeExpression.of(location, "A");
		TypeExpression right = TypeExpression.of(location, "B");
		assertThat(Addition.add(left, right)).hasToString("A+B");
	}

	@Test
	public void shouldStoreTheLocation() throws Exception {
		TypeExpression left = TypeExpression.of(location, "A");
		TypeExpression right = TypeExpression.of(location, "B");
		assertThat(Addition.add(left, right)).hasLocation(location);
	}

	@Test
	public void acceptShouldVisitTheTypeAddition() throws Exception {
		TypeExpression left = TypeExpression.of(location, "A");
		TypeExpression right = TypeExpression.of(location, "B");
		Addition value = Addition.add(left, right);
		value.accept(new Expression.Visitor() {

			@Override
			public void visit(Addition addition) {
				assertThat(value).isSameAs(addition);
			}

			@Override
			public void visit(RealConstant realConstant) {
				fail("Should visit the Addition");
			}

			@Override
			public void visit(StringConstant stringConstant) {
				fail("Should visit the Addition");
			}

			@Override
			public void visit(IntegerConstant integerConstant) {
				fail("Should visit the Addition");
			}

			@Override
			public void visit(TypeExpression typeExpression) {
				fail("Should visit the Addition");
			}
		});
	}
}
