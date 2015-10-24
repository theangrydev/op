package io.github.theangrydev.op.generation;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.github.theangrydev.op.generation.ConstantReference.constantReference;
import static io.github.theangrydev.op.generation.IntegerType.INTEGER_TYPE;
import static io.github.theangrydev.op.generation.RealType.REAL_TYPE;
import static io.github.theangrydev.op.generation.StringType.STRING_TYPE;
import static io.github.theangrydev.op.generation.UnderlyingType.DEFAULT_TYPES;

public class SymbolTable {
	private Map<String, UnderlyingType> defaultUnderlyingTypesByName;
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

	public VariableReference registerVariableReference(String targetTypeName, String existingTypeName) {
		UnderlyingType underlyingType = underlyingType(existingTypeName);
		Preconditions.checkNotNull(underlyingType, "Type with name '%s' does not exist", existingTypeName);
		return variableReferences.registerTypeReference(targetTypeName, underlyingType(existingTypeName));
	}

	private UnderlyingType underlyingType(String typeName) {
		if (isDefaultType(typeName)) {
			return defaultUnderlyingTypesByName.get(typeName);
		}
		return variableReferences.typeReferenceByName(typeName).underlyingType();
	}

	public boolean isDefaultType(String typeName) {
		return defaultUnderlyingTypesByName.containsKey(typeName);
	}

	public VariableReference lookupVariableReference(String typeName) {
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
