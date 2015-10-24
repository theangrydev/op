package io.github.theangrydev.op.generation;

import com.google.common.collect.ImmutableSet;

public interface UnderlyingType {
	void store(ProgramCompiler programCompiler, TypeReference typeReference);
	void load(ProgramCompiler programCompiler, VariableReference variableReference);
	void load(ProgramCompiler programCompiler, ConstantReference constantReference);
	void add(ProgramCompiler programCompiler);
	boolean supportsAdd();

	String name();

	UnderlyingType INTEGER = new UnderlyingType() {
		@Override
		public void store(ProgramCompiler programCompiler, TypeReference typeReference) {
			programCompiler.storeInteger(typeReference);
		}

		@Override
		public void load(ProgramCompiler programCompiler, VariableReference variableReference) {
			programCompiler.loadIntegerFromVariable(variableReference);
		}

		@Override
		public void load(ProgramCompiler programCompiler, ConstantReference constantReference) {
			programCompiler.loadIntegerFromConstant(constantReference);
		}

		@Override
		public void add(ProgramCompiler programCompiler) {
			programCompiler.addTwoIntegers();
		}

		@Override
		public boolean supportsAdd() {
			return true;
		}

		@Override
		public String name() {
			return "Integer";
		}
	};

	UnderlyingType REAL = new UnderlyingType() {
		@Override
		public void store(ProgramCompiler programCompiler, TypeReference typeReference) {
			programCompiler.storeReal(typeReference);
		}

		@Override
		public void load(ProgramCompiler programCompiler, VariableReference variableReference) {
			programCompiler.loadRealFromVariable(variableReference);
		}

		@Override
		public void load(ProgramCompiler programCompiler, ConstantReference constantReference) {
			programCompiler.loadRealFromConstant(constantReference);
		}

		@Override
		public void add(ProgramCompiler programCompiler) {
			programCompiler.addTwoReals();
		}

		@Override
		public boolean supportsAdd() {
			return true;
		}

		@Override
		public String name() {
			return "Real";
		}
	};

	UnderlyingType STRING = new UnderlyingType() {
		@Override
		public void store(ProgramCompiler programCompiler, TypeReference typeReference) {
			programCompiler.storeString(typeReference);
		}

		@Override
		public void load(ProgramCompiler programCompiler, VariableReference variableReference) {
			programCompiler.loadStringFromVariable(variableReference);
		}

		@Override
		public void load(ProgramCompiler programCompiler, ConstantReference constantReference) {
			programCompiler.loadStringFromConstant(constantReference);
		}

		@Override
		public void add(ProgramCompiler programCompiler) {
			programCompiler.addTwoStrings();
		}

		@Override
		public boolean supportsAdd() {
			return true;
		}

		@Override
		public String name() {
			return "String";
		}
	};

	ImmutableSet<UnderlyingType> DEFAULT_TYPES = ImmutableSet.of(INTEGER, REAL, STRING);
}
