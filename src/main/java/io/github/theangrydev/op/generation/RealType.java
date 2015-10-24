package io.github.theangrydev.op.generation;

public class RealType implements UnderlyingType<RealType> {

	public static final RealType REAL_TYPE = new RealType();

	private RealType() {}

	@Override
	public void store(ProgramCompiler programCompiler, VariableReference typeReference) {
		programCompiler.storeReal(typeReference);
	}

	@Override
	public void load(ProgramCompiler programCompiler, VariableReference variableReference) {
		programCompiler.loadRealFromVariable(variableReference);
	}

	@Override
	public void load(ProgramCompiler programCompiler, ConstantReference<RealType> constantReference) {
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

	@Override
	public String toString() {
		return name();
	}
}
