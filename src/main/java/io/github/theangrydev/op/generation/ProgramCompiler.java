/*
 * Copyright 2015 Liam Williams <liam.williams@zoho.com>.
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
