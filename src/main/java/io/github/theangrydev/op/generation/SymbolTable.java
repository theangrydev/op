/*
 * Copyright 2015-2016 Liam Williams <liam.williams@zoho.com>.
 *
 * This file is part of op.
 *
 * op is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * op is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with op.  If not, see <http://www.gnu.org/licenses/>.
 */
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
