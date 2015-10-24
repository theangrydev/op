package io.github.theangrydev.op.generation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VariableReferences {

	private int currentIndex = 1;
	private Map<String, VariableReference<?>> typeReferencesByName;

	public VariableReferences() {
		typeReferencesByName = new HashMap<>();
	}

	public Optional<VariableReference<?>> typeReferenceByName(String name) {
		return Optional.ofNullable(typeReferencesByName.get(name));
	}

	public VariableReference<?> registerTypeReference(String targetTypeName, UnderlyingType<?> underlyingType) {
		VariableReference<?> typeReference = underlyingType.variableReference(currentIndex++, targetTypeName);
		typeReferencesByName.put(targetTypeName, typeReference);
		return typeReference;
	}
}
