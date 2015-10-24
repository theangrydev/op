package io.github.theangrydev.op.generation;

import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.Map;

import static io.github.theangrydev.op.generation.VariableReference.variableReference;

public class VariableReferences {

	private int currentIndex = 1;
	private Map<String, VariableReference> typeReferencesByName;

	public VariableReferences() {
		typeReferencesByName = new HashMap<>();
	}

	public VariableReference typeReferenceByName(String name) {
		VariableReference typeReference = typeReferencesByName.get(name);
		Preconditions.checkNotNull(typeReference, "Type reference with name '%s' not found", name);
		return typeReference;
	}

	public VariableReference registerTypeReference(String targetTypeName, UnderlyingType<?> underlyingType) {
		VariableReference typeReference = variableReference(currentIndex++, targetTypeName, underlyingType);
		typeReferencesByName.put(targetTypeName, typeReference);
		return typeReference;
	}
}
