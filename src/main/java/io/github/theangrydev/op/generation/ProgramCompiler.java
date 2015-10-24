package io.github.theangrydev.op.generation;

public interface ProgramCompiler {
	ConstantReference<IntegerType> registerIntegerConstant(int value);
	ConstantReference<RealType> registerRealConstant(double value);
	ConstantReference<StringType> registerStringConstant(String value);
	VariableReference registerVariableReference(String targetTypeName, UnderlyingType<?> existingType);
	VariableReference lookupVariableReference(String typeName);
	UnderlyingType<?> underlyingType(String typeName);

	void storeInteger(VariableReference typeToStoreIn);
	void storeReal(VariableReference typeToStoreIn);
	void storeString(VariableReference typeToStoreIn);
	void storeReference(VariableReference typeToStoreIn);

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
