package io.github.theangrydev.op.generation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static io.github.theangrydev.op.generation.UnderlyingType.DEFAULT_TYPES;

public class SymbolTable {
	private Map<String, UnderlyingType<?>> defaultUnderlyingTypesByName;
	private VariableReferences variableReferences;

	public SymbolTable(VariableReferences variableReferences) {
		this.variableReferences = variableReferences;
		defaultUnderlyingTypesByName = new HashMap<>();
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

}
