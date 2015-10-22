package io.github.theangrydev.op.language;

import io.github.theangrydev.op.common.WithAssertions;
import io.github.theangrydev.opper.scanner.Location;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class AdditionTest implements WithAssertions {

	private final Location location = mock(Location.class);

	@Test
	public void shouldStoreLeftAndRight() throws Exception {
		TypeExpression left = TypeExpression.typeExpression(location, "A");
		TypeExpression right = TypeExpression.typeExpression(location, "B");
		assertThat(Addition.add(left, right)).hasLeft(left).hasRight(right);
	}

	@Test
	public void shouldToStringTheValue() throws Exception {
		TypeExpression left = TypeExpression.typeExpression(location, "A");
		TypeExpression right = TypeExpression.typeExpression(location, "B");
		assertThat(Addition.add(left, right)).hasToString("A+B");
	}

	//TODO: fix test
	@Ignore
	@Test
	public void shouldStoreTheLocation() throws Exception {
		TypeExpression left = TypeExpression.typeExpression(location, "A");
		TypeExpression right = TypeExpression.typeExpression(location, "B");
		assertThat(Addition.add(left, right)).hasLocation(location);
	}
}
