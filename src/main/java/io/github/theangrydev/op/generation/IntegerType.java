package io.github.theangrydev.op.generation;


public class IntegerType implements UnderlyingType<IntegerType> {

	public static final IntegerType INTEGER_TYPE = new IntegerType();

	private IntegerType() {}

	@Override
	public void store(ProgramCompiler programCompiler, VariableReference<IntegerType> variableReference) {
		programCompiler.storeInteger(variableReference);
	}

	@Override
	public void load(ProgramCompiler programCompiler, VariableReference<IntegerType> variableReference) {
		programCompiler.loadIntegerFromVariable(variableReference);
	}

	@Override
	public void load(ProgramCompiler programCompiler, ConstantReference<IntegerType> constantReference) {
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

	@Override
	public String toString() {
		return name();
	}

	@Override
	public VariableReference<IntegerType> variableReference(int variableIndex, String targetTypeName) {
		return VariableReference.variableReference(variableIndex, targetTypeName, this);
	}
}
