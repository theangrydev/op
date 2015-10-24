package io.github.theangrydev.op.language.expression;

import io.github.theangrydev.op.common.WithAssertions;
import org.junit.Test;

public class TypeExpressionTest implements WithAssertions {

	@Test
	public void shouldStoreTheValue() throws Exception {
		assertThat(TypeExpression.typeExpression(null, "ABC")).hasType("ABC");
	}

	@Test
	public void shouldToStringTheValue() throws Exception {
		assertThat(TypeExpression.typeExpression(null, "ABC")).hasToString("ABC");
	}
}
