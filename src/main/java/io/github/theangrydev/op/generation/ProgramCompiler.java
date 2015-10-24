package io.github.theangrydev.op.generation;

import java.util.Optional;

public interface ProgramCompiler {
	ConstantReference<IntegerType> registerIntegerConstant(int value);
	ConstantReference<RealType> registerRealConstant(double value);
	ConstantReference<StringType> registerStringConstant(String value);

	VariableReference<?> registerVariableReference(String targetTypeName, UnderlyingType<?> existingType);
	Optional<VariableReference<?>> lookupVariableReference(String typeName);
	Optional<UnderlyingType<?>> underlyingType(String typeName);

	void storeInteger(VariableReference<IntegerType> typeToStoreIn);
	void storeReal(VariableReference<RealType> typeToStoreIn);
	void storeString(VariableReference<StringType> typeToStoreIn);
	void storeObject(VariableReference<ObjectType> typeToStoreIn);

	void loadIntegerFromConstant(ConstantReference<IntegerType> constantToLoad);
	void loadRealFromConstant(ConstantReference<RealType> constantToLoad);
	void loadStringFromConstant(ConstantReference<StringType> constantToLoad);
	void loadObjectFromConstant(ConstantReference<ObjectType> constantToLoad);

	void loadIntegerFromVariable(VariableReference<IntegerType> variableToLoad);
	void loadRealFromVariable(VariableReference<RealType> variableToLoad);
	void loadStringFromVariable(VariableReference<StringType> variableToLoad);
	void loadObjectFromVariable(VariableReference<ObjectType> variableToLoad);

	void addTwoIntegers();
	void addTwoReals();
	void addTwoStrings();

	void populateDefaultTypes();
}
