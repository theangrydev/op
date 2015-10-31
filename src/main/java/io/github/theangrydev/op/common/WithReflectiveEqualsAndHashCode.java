package io.github.theangrydev.op.common;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class WithReflectiveEqualsAndHashCode {

	@SuppressWarnings("EqualsWhichDoesntCheckParameterClass") // Handled by EqualsBuilder
	@Override
	public boolean equals(Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
