package io.github.theangrydev.op.generation;

public class VariableReference implements TypeReference {
	private final int index;
	private final String name;
	private final UnderlyingType underlyingType;

	private VariableReference(int index, String name, UnderlyingType underlyingType) {
		this.index = index;
		this.name = name;
		this.underlyingType = underlyingType;
	}

	public static VariableReference variableReference(int index, String name, UnderlyingType underlyingType) {
		return new VariableReference(index, name, underlyingType);
	}

	@Override
	public int index() {
		return index;
	}

	@Override
	public UnderlyingType<?> underlyingType() {
		return underlyingType;
	}

	@Override
	public String toString() {
		return name + ":" + underlyingType.name() + "[" + index + "]";
	}
}
