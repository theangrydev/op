package io.github.theangrydev.op.generation;

public class StringType implements UnderlyingType<StringType> {

	public static StringType STRING_TYPE = new StringType();

	private StringType() {}

	@Override
	public void store(ProgramCompiler programCompiler, TypeReference typeReference) {
		programCompiler.storeString(typeReference);
	}

	@Override
	public void load(ProgramCompiler programCompiler, VariableReference variableReference) {
		programCompiler.loadStringFromVariable(variableReference);
	}

	@Override
	public void load(ProgramCompiler programCompiler, ConstantReference<StringType> constantReference) {
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

	@Override
	public String toString() {
		return name();
	}
}
