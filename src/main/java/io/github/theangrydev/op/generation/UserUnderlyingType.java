package io.github.theangrydev.op.generation;

public class UserUnderlyingType implements UnderlyingType<UserUnderlyingType> {

	private final String name;

	private UserUnderlyingType(String name) {
		this.name = name;
	}

	public static UnderlyingType underlyingType(String name) {
		return new UserUnderlyingType(name);
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public void store(ProgramCompiler programCompiler, VariableReference typeReference) {
		programCompiler.storeReference(typeReference);
	}

	@Override
	public void load(ProgramCompiler programCompiler, VariableReference variableReference) {
		programCompiler.loadReferenceFromVariable(variableReference);
	}

	@Override
	public void load(ProgramCompiler programCompiler, ConstantReference<UserUnderlyingType> constantReference) {
		programCompiler.loadReferenceFromConstant(constantReference);
	}

	@Override
	public void add(ProgramCompiler programCompiler) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean supportsAdd() {
		return false;
	}
}
