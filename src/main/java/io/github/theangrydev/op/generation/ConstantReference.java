package io.github.theangrydev.op.generation;

public class ConstantReference<T extends UnderlyingType<T>> implements TypeReference<T> {
	private final int index;
	private final T underlyingType;

	private ConstantReference(int index, T underlyingType) {
		this.index = index;
		this.underlyingType = underlyingType;
	}

	public static <T extends UnderlyingType<T>> ConstantReference<T> constantReference(int index, T underlyingType) {
		return new ConstantReference<>(index, underlyingType);
	}

	@Override
	public int index() {
		return index;
	}

	@Override
	public T underlyingType() {
		return underlyingType;
	}

	@Override
	public String toString() {
		return underlyingType.name() + "[" + index + "]";
	}

	@Override
	public void load(ProgramCompiler programCompiler) {
		underlyingType.load(programCompiler, this);
	}
}
