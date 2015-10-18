package io.github.theangrydev.op.language;

import io.github.theangrydev.op.common.WithAssertions;
import org.junit.Test;

public class StringConstantTest implements WithAssertions {

	@Test
	public void shouldStoreAValue() throws Exception {
		assertThat(StringConstant.quotedStringConstant(null, "\"100\"")).hasValue("100");
	}

	@Test
	public void shouldToStringTheValue() throws Exception {
		assertThat(StringConstant.quotedStringConstant(null, "\"100\"")).hasToString("100");
	}
}
