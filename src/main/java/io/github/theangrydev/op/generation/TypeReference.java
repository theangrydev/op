package io.github.theangrydev.op.generation;

public interface TypeReference<T extends UnderlyingType<T>> {
	T underlyingType();
	void load(ProgramCompiler programCompiler);
}
