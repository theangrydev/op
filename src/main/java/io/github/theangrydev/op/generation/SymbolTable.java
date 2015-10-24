package io.github.theangrydev.op.generation;

import java.util.*;

import static io.github.theangrydev.op.generation.ConstantReference.constantReference;
import static io.github.theangrydev.op.generation.IntegerType.INTEGER_TYPE;
import static io.github.theangrydev.op.generation.RealType.REAL_TYPE;
import static io.github.theangrydev.op.generation.StringType.STRING_TYPE;
import static io.github.theangrydev.op.generation.UnderlyingType.DEFAULT_TYPES;

public class SymbolTable {
	private Map<String, UnderlyingType<?>> defaultUnderlyingTypesByName;
	private VariableReferences variableReferences;
	private List<ConstantReference<?>> constants;

	public SymbolTable(VariableReferences variableReferences) {
		this.variableReferences = variableReferences;
		defaultUnderlyingTypesByName = new HashMap<>();
		constants = new ArrayList<>();
	}

	public void populateDefaultTypes() {
		for (UnderlyingType defaultType : DEFAULT_TYPES) {
			defaultUnderlyingTypesByName.put(defaultType.name(), defaultType);
		}
	}

	public VariableReference<?> registerVariableReference(String targetTypeName, UnderlyingType<?> existingType) {
		return variableReferences.registerTypeReference(targetTypeName, existingType);
	}

	public Optional<UnderlyingType<?>> underlyingType(String typeName) {
		if (isDefaultType(typeName)) {
			return Optional.of(defaultUnderlyingTypesByName.get(typeName));
		}
		return lookupVariableReference(typeName).map(VariableReference::underlyingType);
	}

	public boolean isDefaultType(String typeName) {
		return defaultUnderlyingTypesByName.containsKey(typeName);
	}

	public Optional<VariableReference<?>> lookupVariableReference(String typeName) {
		return variableReferences.typeReferenceByName(typeName);
	}

	public ConstantReference<IntegerType> registerIntegerConstant() {
		return registerConstant(INTEGER_TYPE);
	}

	public ConstantReference<StringType> registerStringConstant() {
		return registerConstant(STRING_TYPE);
	}

	public ConstantReference<RealType> registerRealConstant() {
		return registerConstant(REAL_TYPE);
	}

	public <T extends UnderlyingType<T>> ConstantReference<T> registerConstant(T underlyingType) {
		int index = constants.size() + 1;
		ConstantReference<T> constantReference = constantReference(index, underlyingType);
		constants.add(constantReference);
		return constantReference;
	}
}
