package io.github.theangrydev.op.language;

import io.github.theangrydev.WithAssertions;
import io.github.theangrydev.opper.scanner.Location;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class TypeDeclarationTest implements WithAssertions {

	private final Location location = mock(Location.class);
	private final TypeExpression target = TypeExpression.of(location, "target");
	private final TypeExpression existing = TypeExpression.of(location, "existing");

	@Test
	public void shouldStoreTargetAndExistingType() throws Exception {
		assertThat(TypeDeclaration.of(target, existing))
			.hasTargetType(target)
			.hasExistingType(existing);
	}

	@Test
	public void shouldToStringTheValue() throws Exception {
		assertThat(TypeDeclaration.of(target, existing)).hasToString("target:existing");
	}
}
