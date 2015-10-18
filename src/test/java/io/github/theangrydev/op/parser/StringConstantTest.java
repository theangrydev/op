package io.github.theangrydev.op.parser;

import io.github.theangrydev.WithAssertions;
import org.junit.Test;

public class StringConstantTest implements WithAssertions {

	@Test
	public void shouldStoreAValue() throws Exception {
		assertThat(StringConstant.of(null, "\"100\"")).hasValue("100");
	}

	@Test
	public void shouldToStringTheValue() throws Exception {
		assertThat(StringConstant.of(null, "\"100\"")).hasToString("100");
	}
}
