package io.github.theangrydev.op.parser;

import io.github.theangrydev.WithAssertions;
import org.junit.Test;

public class TypeDeclarationTest implements WithAssertions {

	@Test
	public void shouldStoreTargetAndExistingType() throws Exception {
		assertThat(TypeDeclaration.of("target", "existing"))
			.hasTargetType("target")
			.hasExistingType("existing");
	}

	@Test
	public void shouldToStringTheValue() throws Exception {
		assertThat(TypeDeclaration.of("target", "existing")).hasToString("target:existing");
	}
}
