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

import io.github.theangrydev.op.generation.jvm.classfile.ClassFile;
import io.github.theangrydev.op.generation.jvm.constant.ConstantPool;
import io.github.theangrydev.op.generation.jvm.constant.ConstantPoolIndex;

import java.util.Optional;

import static io.github.theangrydev.op.generation.ConstantReference.constantReference;
import static io.github.theangrydev.op.generation.IntegerType.INTEGER_TYPE;
import static io.github.theangrydev.op.generation.RealType.REAL_TYPE;
import static io.github.theangrydev.op.generation.StringType.STRING_TYPE;

public class SymbolTableProgramCompiler implements ProgramCompiler {

	private final SymbolTable symbolTable;
	private final ConstantPool constantPool;

	public SymbolTableProgramCompiler(SymbolTable symbolTable) {
		this.symbolTable = symbolTable;

		this.constantPool = ConstantPool.constantPool();
	}

	public ClassFile classFile() {
		return ClassFile.classFile(constantPool);
	}

	@Override
	public ConstantReference<IntegerType> registerIntegerConstant(int value) {
		ConstantPoolIndex integerIndex = constantPool.addInteger(value);
		return constantReference(integerIndex, INTEGER_TYPE);
	}

	@Override
	public ConstantReference<RealType> registerRealConstant(double value) {
		return constantReference(null, REAL_TYPE);
	}

	@Override
	public ConstantReference<StringType> registerStringConstant(String value) {
		return constantReference(null, STRING_TYPE);
	}

	@Override
	public VariableReference<?> registerVariableReference(String targetTypeName, UnderlyingType<?> existingType) {
		return symbolTable.registerVariableReference(targetTypeName, existingType);
	}

	@Override
	public Optional<VariableReference<?>> lookupVariableReference(String typeName) {
		return symbolTable.lookupVariableReference(typeName);
	}

	@Override
	public Optional<UnderlyingType<?>> underlyingType(String typeName) {
		return symbolTable.underlyingType(typeName);
	}

	@Override
	public void storeInteger(VariableReference<IntegerType> typeToStoreIn) {

	}

	@Override
	public void storeReal(VariableReference<RealType> typeToStoreIn) {

	}

	@Override
	public void storeString(VariableReference<StringType> typeToStoreIn) {

	}

	@Override
	public void storeObject(VariableReference<ObjectType> typeToStoreIn) {

	}

	@Override
	public void loadIntegerFromConstant(ConstantReference<IntegerType> constantToLoad) {

	}

	@Override
	public void loadRealFromConstant(ConstantReference<RealType> constantToLoad) {

	}

	@Override
	public void loadStringFromConstant(ConstantReference<StringType> constantToLoad) {

	}

	@Override
	public void loadObjectFromConstant(ConstantReference<ObjectType> constantToLoad) {

	}

	@Override
	public void loadIntegerFromVariable(VariableReference<IntegerType> variableToLoad) {

	}

	@Override
	public void loadRealFromVariable(VariableReference<RealType> variableToLoad) {

	}

	@Override
	public void loadStringFromVariable(VariableReference<StringType> variableToLoad) {

	}

	@Override
	public void loadObjectFromVariable(VariableReference<ObjectType> variableToLoad) {

	}

	@Override
	public void addTwoIntegers() {

	}

	@Override
	public void addTwoReals() {

	}

	@Override
	public void addTwoStrings() {

	}

	@Override
	public void populateDefaultTypes() {
		symbolTable.populateDefaultTypes();
	}
}
