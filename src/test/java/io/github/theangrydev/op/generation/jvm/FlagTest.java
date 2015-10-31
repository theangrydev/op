package io.github.theangrydev.op.generation.jvm;

import io.github.theangrydev.op.common.WithAssertions;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;

public class FlagTest implements WithAssertions {

	@Test
	public void combinesFlagsIntoAnInteger() {
		assertThat(Flag.combine(newArrayList(() -> 0x001, () -> 0x010, () -> 0x100))).isEqualTo(0x111);
	}
}
