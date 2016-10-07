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
package io.github.theangrydev.op.generation.jvm.constant;

import io.github.theangrydev.op.generation.jvm.ClassFileWriter;
import io.github.theangrydev.op.generation.jvm.ShortValue;

import java.io.DataOutput;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.github.theangrydev.op.generation.jvm.ShortValue.shortValue;
import static io.github.theangrydev.op.generation.jvm.constant.ClassInfoConstant.classInfo;
import static io.github.theangrydev.op.generation.jvm.constant.Constant.constant;
import static io.github.theangrydev.op.generation.jvm.constant.ConstantPoolIndex.constantPoolIndex;
import static io.github.theangrydev.op.generation.jvm.constant.FieldReferenceInfoConstant.fieldReferenceInfoConstant;
import static io.github.theangrydev.op.generation.jvm.constant.IntegerConstantInfo.integerConstant;
import static io.github.theangrydev.op.generation.jvm.constant.MethodReferenceInfoConstant.methodReferenceInfoConstant;
import static io.github.theangrydev.op.generation.jvm.constant.NameAndTypeInfoConstant.nameAndTypeInfoConstant;
import static io.github.theangrydev.op.generation.jvm.constant.Utf8InfoConstant.utf8InfoConstant;


public class ConstantPool implements ClassFileWriter {

	private final Map<Constant, Integer> constantIndexes;
	private int nextIndex = 1;

	private ConstantPool() {
		constantIndexes = new LinkedHashMap<>();
	}

	public static ConstantPool constantPool() {
		return new ConstantPool();
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		ShortValue constantPoolCount = shortValue(constantIndexes.size() + 1);
		constantPoolCount.writeTo(dataOutput);
		for (Constant constant : constantIndexes.keySet()) {
			constant.writeTo(dataOutput);
		}
	}

	private <T extends ConstantInfo> ConstantPoolIndex<T> addConstant(T constantInfo) {
		return addConstant(constantInfo, 1);
	}

	private <T extends ConstantInfo> ConstantPoolIndex<T> addConstant(T constantInfo, int numberOfIndexSlotsUsed) {
		Constant constant = constant(constantInfo);
		if (constantIndexes.containsKey(constant)) {
			return constantPoolIndex(constantIndexes.get(constant));
		}
		constantIndexes.put(constant, nextIndex);
		ConstantPoolIndex<T> constantPoolIndex = constantPoolIndex(nextIndex);
		nextIndex += numberOfIndexSlotsUsed;
		return constantPoolIndex;
	}

	public ConstantPoolIndex<IntegerConstantInfo> addInteger(int value) {
		return addConstant(integerConstant(value));
	}

	public ConstantPoolIndex<Utf8InfoConstant> addUtf8Info(String value) {
		return addConstant(utf8InfoConstant(value));
	}

	public ConstantPoolIndex<ClassInfoConstant> addClassInfo(String className) {
		return addConstant(classInfo(addUtf8Info(className)));
	}

	public ConstantPoolIndex<FieldReferenceInfoConstant> addFieldReference(String classWithField, String fieldName, String fieldType) {
		return addConstant(fieldReferenceInfoConstant(addClassInfo(classWithField), addNameAndType(addUtf8Info(fieldName), addUtf8Info(fieldType))));
	}

	private ConstantPoolIndex<NameAndTypeInfoConstant> addNameAndType(ConstantPoolIndex<Utf8InfoConstant> nameIndex, ConstantPoolIndex<Utf8InfoConstant> descriptorIndex) {
		return addConstant(nameAndTypeInfoConstant(nameIndex, descriptorIndex));
	}

	public ConstantPoolIndex<MethodReferenceInfoConstant> addMethodReference(String classWithMethod, String methodName, String methodSignature) {
		return addConstant(methodReferenceInfoConstant(addClassInfo(classWithMethod), addNameAndType(addUtf8Info(methodName), addUtf8Info(methodSignature))));
	}
}
