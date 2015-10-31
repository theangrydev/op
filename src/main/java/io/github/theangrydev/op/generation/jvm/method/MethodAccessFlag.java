package io.github.theangrydev.op.generation.jvm.method;

import io.github.theangrydev.op.generation.jvm.Flag;

public enum MethodAccessFlag implements Flag {
	PUBLIC(0x0001),
	STATIC(0x0008);

	private final int value;

	MethodAccessFlag(int value) {
		this.value = value;
	}

	public static MethodAccessFlag methodAccessFlag(String name) {
		return MethodAccessFlag.valueOf(name);
	}

	@Override
	public int value() {
		return value;
	}
}
