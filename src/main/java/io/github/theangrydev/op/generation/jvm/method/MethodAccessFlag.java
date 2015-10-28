package io.github.theangrydev.op.generation.jvm.method;

import java.util.Set;

public enum MethodAccessFlag {
	PUBLIC(0x0001),
	STATIC(0x0008);

	private int value;

	MethodAccessFlag(int value) {
		this.value = value;
	}

	public int value() {
		return value;
	}

	public static int combine(Set<MethodAccessFlag> flags) {
		return flags.stream()
			.mapToInt(MethodAccessFlag::value)
			.reduce(0, (flagsSoFar, flagToOr) -> flagsSoFar | flagToOr);
	}
}
