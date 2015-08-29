package io.github.theangrydev;

import java_cup.runtime.Symbol;
import java_cup.runtime.SymbolAssert;

public interface WithAssertions extends org.assertj.core.api.WithAssertions {

	default SymbolAssert assertThat(Symbol actual) {
		return new SymbolAssert(actual);
	}
}
