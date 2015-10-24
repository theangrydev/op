package io.github.theangrydev.op.generation;

public class ConstantReference implements TypeReference {
	private final int index;
	private final UnderlyingType underlyingType;

	private ConstantReference(int index, UnderlyingType underlyingType) {
		this.index = index;
		this.underlyingType = underlyingType;
	}

	public static ConstantReference constantReference(int index, UnderlyingType underlyingType) {
		return new ConstantReference(index, underlyingType);
	}

	@Override
	public int index() {
		return index;
	}

	@Override
	public UnderlyingType underlyingType() {
		return underlyingType;
	}

	@Override
	public String toString() {
		return underlyingType.name() + "[" + index + "]";
	}
}
