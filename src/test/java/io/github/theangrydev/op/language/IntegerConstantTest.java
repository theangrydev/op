package io.github.theangrydev.op.language;

import io.github.theangrydev.op.common.WithAssertions;
import io.github.theangrydev.opper.scanner.Location;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class IntegerConstantTest implements WithAssertions {
	private final Location location = mock(Location.class);

	@Test
	public void shouldStoreAValue() throws Exception {
		assertThat(IntegerConstant.integerConstant(location, "100")).hasValue(100);
	}

	@Test
	public void shouldToStringTheValue() throws Exception {
		assertThat(IntegerConstant.integerConstant(location, "100")).hasToString("100");
	}
}
