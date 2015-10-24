package io.github.theangrydev.op.generation;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.github.theangrydev.op.generation.ConstantReference.constantReference;
import static io.github.theangrydev.op.generation.UnderlyingType.*;

public class SymbolTable {
	private Map<String, UnderlyingType> defaultUnderlyingTypesByName;
	private VariableReferences variableReferences;
	private List<TypeReference> constants;

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

	public ConstantReference registerIntegerConstant() {
		return registerConstant(INTEGER);
	}

	public ConstantReference registerStringConstant() {
		return registerConstant(STRING);
	}

	public ConstantReference registerRealConstant() {
		return registerConstant(REAL);
	}

	public ConstantReference registerConstant(UnderlyingType underlyingType) {
		int index = constants.size() + 1;
		ConstantReference constantReference = constantReference(index, underlyingType);
		constants.add(constantReference);
		return constantReference;
	}
}
