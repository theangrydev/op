package io.github.theangrydev.op.generation;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

import static io.github.theangrydev.op.generation.IntegerType.INTEGER_TYPE;
import static io.github.theangrydev.op.generation.RealType.REAL_TYPE;
import static io.github.theangrydev.op.generation.StringType.STRING_TYPE;

public interface UnderlyingType<T extends UnderlyingType<T>> {
	void store(ProgramCompiler programCompiler, TypeReference typeReference);
	void load(ProgramCompiler programCompiler, VariableReference variableReference);
	void load(ProgramCompiler programCompiler, ConstantReference<T> constantReference);
	void add(ProgramCompiler programCompiler);
	boolean supportsAdd();
	String name();
	Set<UnderlyingType> DEFAULT_TYPES = ImmutableSet.of(INTEGER_TYPE, REAL_TYPE, STRING_TYPE);
}
