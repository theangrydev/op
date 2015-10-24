package io.github.theangrydev.op.language;

import io.github.theangrydev.op.common.WithAssertions;
import io.github.theangrydev.op.language.expression.RealConstant;
import org.junit.Test;

public class RealConstantTest implements WithAssertions {

	@Test
	public void shouldStoreAValue() throws Exception {
		assertThat(RealConstant.realConstant(null, "10.5")).hasValue(10.5);
	}

	@Test
	public void shouldToStringTheValue() throws Exception {
		assertThat(RealConstant.realConstant(null, "100.105")).hasToString("100.105");
	}
}
