package io.github.theangrydev.op.generation;

public interface ProgramCompiler {
	ConstantReference<IntegerType> registerIntegerConstant(int value);
	ConstantReference<RealType> registerRealConstant(double value);
	ConstantReference<StringType> registerStringConstant(String value);
	VariableReference registerVariableReference(String targetTypeName, String existingTypeName);
	VariableReference lookupVariableReference(String typeName);

	void storeInteger(TypeReference typeToStoreIn);
	void storeReal(TypeReference typeToStoreIn);
	void storeString(TypeReference typeToStoreIn);
	void storeReference(TypeReference typeToStoreIn);

	void loadIntegerFromConstant(ConstantReference<IntegerType> constantToLoad);
	void loadRealFromConstant(ConstantReference<RealType> constantToLoad);
	void loadStringFromConstant(ConstantReference<StringType> constantToLoad);
	void loadReferenceFromConstant(ConstantReference<UserUnderlyingType> constantToLoad);

	void loadIntegerFromVariable(VariableReference variableToLoad);
	void loadRealFromVariable(VariableReference variableToLoad);
	void loadStringFromVariable(VariableReference variableToLoad);
	void loadReferenceFromVariable(VariableReference variableToLoad);

	void addTwoIntegers();
	void addTwoReals();
	void addTwoStrings();

	void populateDefaultTypes();
}
