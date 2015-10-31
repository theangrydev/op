package io.github.theangrydev.op.generation;

public class VariableReference<T extends UnderlyingType<T>> implements TypeReference<T> {
	private final int index;
	private final String name;
	private final T underlyingType;

	private VariableReference(int index, String name, T underlyingType) {
		this.index = index;
		this.name = name;
		this.underlyingType = underlyingType;
	}

	public static <T extends UnderlyingType<T>> VariableReference<T> variableReference(int index, String name, T underlyingType) {
		return new VariableReference<>(index, name, underlyingType);
	}

	@Override
	public T underlyingType() {
		return underlyingType;
	}

	@Override
	public String toString() {
		return name + ":" + underlyingType.name() + "[" + index + "]";
	}

	@Override
	public void load(ProgramCompiler programCompiler) {
		underlyingType.load(programCompiler, this);
	}

	public void store(ProgramCompiler programCompiler) {
		underlyingType.store(programCompiler, this);
	}
}
