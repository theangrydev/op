package io.github.theangrydev.op.generation;

public interface ProgramCompiler {
	ConstantReference registerIntegerConstant(int value);
	ConstantReference registerRealConstant(double value);
	ConstantReference registerStringConstant(String value);
	VariableReference registerVariableReference(String targetTypeName, String existingTypeName);
	VariableReference lookupVariableReference(String typeName);

	void storeInteger(TypeReference typeToStoreIn);
	void storeReal(TypeReference typeToStoreIn);
	void storeString(TypeReference typeToStoreIn);
	void storeReference(TypeReference typeToStoreIn);

	void loadIntegerFromConstant(ConstantReference constantToLoad);
	void loadRealFromConstant(ConstantReference constantToLoad);
	void loadStringFromConstant(ConstantReference constantToLoad);
	void loadReferenceFromConstant(ConstantReference constantToLoad);

	void loadIntegerFromVariable(VariableReference variableToLoad);
	void loadRealFromVariable(VariableReference variableToLoad);
	void loadStringFromVariable(VariableReference variableToLoad);
	void loadReferenceFromVariable(VariableReference variableToLoad);

	void addTwoIntegers();
	void addTwoReals();
	void addTwoStrings();

	void populateDefaultTypes();
}
