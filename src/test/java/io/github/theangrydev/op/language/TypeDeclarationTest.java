package io.github.theangrydev.op.language;

import io.github.theangrydev.op.common.WithAssertions;
import io.github.theangrydev.op.language.assignment.Type;
import io.github.theangrydev.op.language.assignment.TypeDeclaration;
import io.github.theangrydev.op.language.expression.TypeExpression;
import io.github.theangrydev.opper.scanner.Location;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class TypeDeclarationTest implements WithAssertions {

	private final Location location = mock(Location.class);
	private final TypeExpression target = TypeExpression.typeExpression(location, "target");
	private final Type existing = Type.type(location, "existing");

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
