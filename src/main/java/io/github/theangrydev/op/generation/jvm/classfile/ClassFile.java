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
package io.github.theangrydev.op.generation.jvm.classfile;

import io.github.theangrydev.op.generation.jvm.ClassFileWriter;
import io.github.theangrydev.op.generation.jvm.attribute.Attribute;
import io.github.theangrydev.op.generation.jvm.attribute.AttributeInfo;
import io.github.theangrydev.op.generation.jvm.attribute.Attributes;
import io.github.theangrydev.op.generation.jvm.attribute.instruction.Instructions;
import io.github.theangrydev.op.generation.jvm.constant.*;
import io.github.theangrydev.op.generation.jvm.field.Fields;
import io.github.theangrydev.op.generation.jvm.method.MethodInfo;
import io.github.theangrydev.op.generation.jvm.method.Methods;

import java.io.DataOutput;
import java.io.IOException;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static io.github.theangrydev.op.generation.jvm.ByteValue.byteValue;
import static io.github.theangrydev.op.generation.jvm.ShortValue.shortValue;
import static io.github.theangrydev.op.generation.jvm.attribute.Attribute.attribute;
import static io.github.theangrydev.op.generation.jvm.attribute.Attributes.attributes;
import static io.github.theangrydev.op.generation.jvm.attribute.CodeAttributeInfo.codeAttributeInfo;
import static io.github.theangrydev.op.generation.jvm.attribute.ExceptionTable.exceptionTable;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Aload0.aload0;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Bipush.bipush;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Getstatic.getstatic;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Iadd.iadd;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Iconst2.iconst2;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Iconst3.iconst3;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Iload0.iload0;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Iload1.iload1;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Iload2.iload2;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Instructions.instructions;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Invokespecial.invokespecial;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Invokestatic.invokestatic;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Invokevirtual.invokevirtual;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Ireturn.ireturn;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Istore1.istore1;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.Istore2.istore2;
import static io.github.theangrydev.op.generation.jvm.attribute.instruction.VoidReturn.voidreturn;
import static io.github.theangrydev.op.generation.jvm.classfile.ClassAccessFlag.TREAT_SUPER_METHODS_SPECIALLY;
import static io.github.theangrydev.op.generation.jvm.classfile.ClassAccessFlags.classAccessFlags;
import static io.github.theangrydev.op.generation.jvm.classfile.Version.java8;
import static io.github.theangrydev.op.generation.jvm.method.MethodAccessFlag.PUBLIC;
import static io.github.theangrydev.op.generation.jvm.method.MethodAccessFlag.STATIC;
import static io.github.theangrydev.op.generation.jvm.method.MethodAccessFlags.methodAccessFlags;
import static io.github.theangrydev.op.generation.jvm.method.MethodInfo.methodInfo;
import static io.github.theangrydev.op.generation.jvm.method.Methods.methods;
import static java.util.Collections.emptyList;

public class ClassFile implements ClassFileWriter {

	private final Version version = java8();
	private final ConstantPool constantPool;
	private final ClassAccessFlags accessFlags;
	private final Interfaces classInterfaces = new Interfaces();
	private final Fields classFields = new Fields();
	private final Methods classMethods;
	private final Attributes classAttributes = attributes(emptyList());
	private final ConstantPoolIndex<ClassInfoConstant> thisIndex;
	private final ConstantPoolIndex<ClassInfoConstant> superIndex;

	private ClassFile(ConstantPool constantPool, List<MethodInfo> classMethods) {
		this.classMethods = methods(classMethods);
		this.constantPool = constantPool;
		this.accessFlags = classAccessFlags(ClassAccessFlag.PUBLIC, TREAT_SUPER_METHODS_SPECIALLY);
		thisIndex = constantPool.addClassInfo("Test");
		superIndex = constantPool.addClassInfo("java/lang/Object");
	}

	public static ClassFile classFile(ConstantPool constantPool) {
		return new ClassFile(constantPool, newArrayList(mainMethod(constantPool), someCalculation(constantPool), constructor(constantPool)));
	}

	private static MethodInfo constructor(ConstantPool constantPool) {
		ConstantPoolIndex<Utf8InfoConstant> nameIndex = constantPool.addUtf8Info("<init>");
		ConstantPoolIndex<Utf8InfoConstant> descriptorIndex = constantPool.addUtf8Info("()V");
		ConstantPoolIndex<MethodReferenceInfoConstant> objectConstructor = constantPool.addMethodReference("java/lang/Object", "<init>", "()V");
		Instructions instructions = instructions(aload0(), invokespecial(objectConstructor), voidreturn());
		AttributeInfo attributeInfo = codeAttributeInfo(shortValue(2), shortValue(3), instructions, exceptionTable(), attributes(emptyList()));
		Attribute code = attribute(constantPool.addUtf8Info("Code"), attributeInfo);
		Attributes attributes = attributes(newArrayList(code));
		return methodInfo(nameIndex, descriptorIndex, attributes, methodAccessFlags(newHashSet(PUBLIC)));
	}

	private static MethodInfo someCalculation(ConstantPool constantPool) {
		ConstantPoolIndex<Utf8InfoConstant> nameIndex = constantPool.addUtf8Info("someCalculation");
		ConstantPoolIndex<Utf8InfoConstant> descriptorIndex = constantPool.addUtf8Info("(I)I");
		Instructions instructions = instructions(iconst2(), istore1(), iconst3(), istore2(), iload1(), iload2(), iadd(), iload0(), iadd(), ireturn());
		AttributeInfo attributeInfo = codeAttributeInfo(shortValue(2), shortValue(3), instructions, exceptionTable(), attributes(emptyList()));
		Attribute code = attribute(constantPool.addUtf8Info("Code"), attributeInfo);
		Attributes attributes = attributes(newArrayList(code));
		return methodInfo(nameIndex, descriptorIndex, attributes, methodAccessFlags(newHashSet(PUBLIC, STATIC)));
	}

	private static MethodInfo mainMethod(ConstantPool constantPool) {
		ConstantPoolIndex<Utf8InfoConstant> nameIndex = constantPool.addUtf8Info("main");
		ConstantPoolIndex<Utf8InfoConstant> descriptorIndex = constantPool.addUtf8Info("([Ljava/lang/String;)V");
		ConstantPoolIndex<FieldReferenceInfoConstant> systemOut = constantPool.addFieldReference("java/lang/System", "out", "Ljava/io/PrintStream;");
		ConstantPoolIndex<MethodReferenceInfoConstant> someCalculation = constantPool.addMethodReference("Test", "someCalculation", "(I)I");
		ConstantPoolIndex<MethodReferenceInfoConstant> println = constantPool.addMethodReference("java/io/PrintStream", "println", "(I)V");
		Instructions instructions = instructions(getstatic(systemOut), bipush(byteValue(11)), invokestatic(someCalculation), invokevirtual(println), voidreturn());
		AttributeInfo attributeInfo = codeAttributeInfo(shortValue(2), shortValue(1), instructions, exceptionTable(), attributes(emptyList()));
		Attribute code = attribute(constantPool.addUtf8Info("Code"), attributeInfo);
		Attributes attributes = attributes(newArrayList(code));
		return methodInfo(nameIndex, descriptorIndex, attributes, methodAccessFlags(newHashSet(PUBLIC, STATIC)));
	}

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		writeMagicNumber(dataOutput);
		version.writeTo(dataOutput);
		constantPool.writeTo(dataOutput);
		accessFlags.writeTo(dataOutput);
		thisIndex.writeTo(dataOutput);
		superIndex.writeTo(dataOutput);
		classInterfaces.writeTo(dataOutput);
		classFields.writeTo(dataOutput);
		classMethods.writeTo(dataOutput);
		classAttributes.writeTo(dataOutput);
	}

	private void writeMagicNumber(DataOutput dataOutput) throws IOException {
		byteValue(0xCA).writeTo(dataOutput);
		byteValue(0xFE).writeTo(dataOutput);
		byteValue(0xBA).writeTo(dataOutput);
		byteValue(0xBE).writeTo(dataOutput);
	}
}
