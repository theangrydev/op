package io.github.theangrydev.op.generation;

public class ObjectType implements UnderlyingType<ObjectType> {

	private final String name;

	private ObjectType(String name) {
		this.name = name;
	}

	public static UnderlyingType underlyingType(String name) {
		return new ObjectType(name);
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public void store(ProgramCompiler programCompiler, VariableReference<ObjectType> variableReference) {
		programCompiler.storeObject(variableReference);
	}

	@Override
	public void load(ProgramCompiler programCompiler, VariableReference<ObjectType> variableReference) {
		programCompiler.loadObjectFromVariable(variableReference);
	}

	@Override
	public void load(ProgramCompiler programCompiler, ConstantReference<ObjectType> constantReference) {
		programCompiler.loadObjectFromConstant(constantReference);
	}

	@Override
	public VariableReference<ObjectType> variableReference(int variableIndex, String targetTypeName) {
		return VariableReference.variableReference(variableIndex, targetTypeName, this);
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
