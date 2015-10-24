package io.github.theangrydev.op.generation;

public interface TypeReference<T extends UnderlyingType<T>> {
	int index();
	T underlyingType();
	void load(ProgramCompiler programCompiler);
}
