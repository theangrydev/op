package io.github.theangrydev.op.generation.jvm.classfile;

import io.github.theangrydev.op.generation.jvm.Flag;

public enum ClassAccessFlag implements Flag {
	PUBLIC(0x0001),
	TREAT_SUPER_METHODS_SPECIALLY(0x0020);

	private final int value;

	ClassAccessFlag(int value) {
		this.value = value;
	}

	public static ClassAccessFlag classAccessFlag(String name) {
		return valueOf(name);
	}

	@Override
	public int value() {
		return value;
	}
}
