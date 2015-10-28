package io.github.theangrydev.op.generation.jvm.constant;

import io.github.theangrydev.op.generation.jvm.ClassFileWriter;
import io.github.theangrydev.op.generation.jvm.ShortValue;

import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.github.theangrydev.op.generation.jvm.ShortValue.shortValue;
import static io.github.theangrydev.op.generation.jvm.constant.ClassInfoConstant.classInfo;
import static io.github.theangrydev.op.generation.jvm.constant.Constant.constant;
import static io.github.theangrydev.op.generation.jvm.constant.ConstantPoolIndex.constantPoolIndex;
import static io.github.theangrydev.op.generation.jvm.constant.FieldReferenceInfoConstant.fieldReferenceInfoConstant;
import static io.github.theangrydev.op.generation.jvm.constant.IntegerConstant.integerConstant;
import static io.github.theangrydev.op.generation.jvm.constant.MethodReferenceInfoConstant.methodReferenceInfoConstant;
import static io.github.theangrydev.op.generation.jvm.constant.NameAndTypeInfoConstant.nameAndTypeInfoConstant;
import static io.github.theangrydev.op.generation.jvm.constant.Utf8InfoConstant.utf8InfoConstant;


public class ConstantPool implements ClassFileWriter {

	private final List<Constant> constants;

	private ConstantPool() {
		constants = new ArrayList<>();
	}

	public static ConstantPool constantPool() {
		return new ConstantPool();
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		ShortValue constantPoolCount = shortValue(constants.size() + 1);
		constantPoolCount.writeTo(dataOutput);
		for (Constant constant : constants) {
			constant.writeTo(dataOutput);
		}
	}

	public ConstantPoolIndex addInteger(int value) {
		return addConstant(integerConstant(value));
	}

	public ConstantPoolIndex addUtf8Info(String value) {
		return addConstant(utf8InfoConstant(value));
	}

	private ConstantPoolIndex addConstant(ConstantInfo constantInfo) {
		constants.add(constant(constantInfo));
		return constantPoolIndex((short) constants.size());
	}

	public ConstantPoolIndex addClassInfo(String className) {
		return addConstant(classInfo(addUtf8Info(className)));
	}

	public ConstantPoolIndex addFieldReference(String classWithField, String fieldName, String fieldType) {
		return addConstant(fieldReferenceInfoConstant(addClassInfo(classWithField), addNameAndType(addUtf8Info(fieldName), addUtf8Info(fieldType))));
	}

	private ConstantPoolIndex addNameAndType(ConstantPoolIndex nameIndex, ConstantPoolIndex descriptorIndex) {
		return addConstant(nameAndTypeInfoConstant(nameIndex, descriptorIndex));
	}

	public ConstantPoolIndex addMethodReference(String classWithMethod, String methodName, String methodSignature) {
		return addConstant(methodReferenceInfoConstant(addClassInfo(classWithMethod), addNameAndType(addUtf8Info(methodName), addUtf8Info(methodSignature))));
	}
}
