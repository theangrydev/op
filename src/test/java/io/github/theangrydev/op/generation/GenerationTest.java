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

import io.github.theangrydev.op.common.WithAssertions;
import io.github.theangrydev.op.generation.jvm.classfile.ClassFile;
import io.github.theangrydev.op.language.Program;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.github.theangrydev.op.parser.ProgramSemanticAnalyserFactory.PROGRAM_SEMANTIC_ANALYSER_FACTORY;
import static java.util.stream.Collectors.joining;

public class GenerationTest implements WithAssertions {

	//TODO: test this properly
//	private final ProgramCompiler programCompiler = new ProgramCompiler() {
//
//		private final SymbolTable symbolTable = new SymbolTable(new VariableReferences());
//
//		@Override
//		public void addTwoIntegers() {
//			System.out.println("addTwoIntegers");
//		}
//
//		@Override
//		public void addTwoReals() {
//			System.out.println("addTwoReals");
//		}
//
//		@Override
//		public void addTwoStrings() {
//			System.out.println("addTwoStrings");
//		}
//
//		@Override
//		public void populateDefaultTypes() {
//			System.out.println("populateDefaultTypes");
//			symbolTable.populateDefaultTypes();
//		}
//
//		@Override
//		public ConstantReference<IntegerType> registerIntegerConstant(int value) {
//			System.out.println("registerIntegerConstant(" + value + ")");
//			return symbolTable.registerIntegerConstant(integerIndex);
//		}
//
//		@Override
//		public ConstantReference<RealType> registerRealConstant(double value) {
//			System.out.println("registerRealConstant(" + value + ")");
//			return symbolTable.registerRealConstant();
//		}
//
//		@Override
//		public ConstantReference<StringType> registerStringConstant(String value) {
//			System.out.println("registerStringConstant(" + value + ")");
//			return symbolTable.registerStringConstant();
//		}
//
//		@Override
//		public VariableReference<?> registerVariableReference(String targetTypeName, UnderlyingType<?> existingType) {
//			System.out.println("registerVariableReference(" + targetTypeName + "," + existingType + ")");
//			return symbolTable.registerVariableReference(targetTypeName, existingType);
//		}
//
//		@Override
//		public Optional<VariableReference<?>> lookupVariableReference(String typeName) {
//			System.out.println("lookupVariableReference(" + typeName + ")");
//			return symbolTable.lookupVariableReference(typeName);
//		}
//
//		@Override
//		public Optional<UnderlyingType<?>> underlyingType(String typeName) {
//			System.out.println("underlyingType(" + typeName + ")");
//			return symbolTable.underlyingType(typeName);
//		}
//
//		@Override
//		public void storeInteger(VariableReference<IntegerType> typeToStoreIn) {
//			System.out.println("storeInteger(" + typeToStoreIn + ")");
//		}
//
//		@Override
//		public void storeReal(VariableReference<RealType> typeToStoreIn) {
//			System.out.println("storeReal(" + typeToStoreIn + ")");
//		}
//
//		@Override
//		public void storeString(VariableReference<StringType> typeToStoreIn) {
//			System.out.println("storeString(" + typeToStoreIn + ")");
//		}
//
//		@Override
//		public void storeObject(VariableReference<ObjectType> typeToStoreIn) {
//			System.out.println("storeReference(" + typeToStoreIn + ")");
//		}
//
//		@Override
//		public void loadIntegerFromConstant(ConstantReference<IntegerType> constantToLoad) {
//			System.out.println("loadIntegerFromConstant(" + constantToLoad + ")");
//		}
//
//		@Override
//		public void loadIntegerFromVariable(VariableReference<IntegerType> variableToLoad) {
//			System.out.println("loadIntegerVariable(" + variableToLoad + ")");
//		}
//
//		@Override
//		public void loadRealFromConstant(ConstantReference<RealType> constantToLoad) {
//			System.out.println("loadRealFromConstant(" + constantToLoad + ")");
//		}
//
//		@Override
//		public void loadRealFromVariable(VariableReference<RealType> variableToLoad) {
//			System.out.println("loadRealFromVariable(" + variableToLoad + ")");
//		}
//
//		@Override
//		public void loadStringFromConstant(ConstantReference<StringType> constantToLoad) {
//			System.out.println("loadStringFromConstant(" + constantToLoad + ")");
//		}
//
//		@Override
//		public void loadStringFromVariable(VariableReference<StringType> variableToLoad) {
//			System.out.println("loadStringFromVariable(" + variableToLoad + ")");
//		}
//
//		@Override
//		public void loadObjectFromConstant(ConstantReference constantToLoad) {
//			System.out.println("loadReferenceFromConstant(" + constantToLoad + ")");
//		}
//
//		@Override
//		public void loadObjectFromVariable(VariableReference<ObjectType> variableToLoad) {
//			System.out.println("loadReferenceFromVariable(" + variableToLoad + ")");
//		}
//	};

	@Test
	public void shouldGenerateCode() throws IOException, InterruptedException {
		SymbolTableProgramCompiler programCompiler = new SymbolTableProgramCompiler(new SymbolTable(new VariableReferences()));
		Program program = PROGRAM_SEMANTIC_ANALYSER_FACTORY.programSemanticAnalyser(new StringReader("Count:Integer=6+4+2;A:Count=Count+Count;")).analyse().get();
		program.simplify();
		program.checkTypes(programCompiler);
		program.compile(programCompiler);

		ClassFile classFile = programCompiler.classFile();

		Path tempDirectory = Files.createTempDirectory(getClass().getSimpleName());
		Path tempFile = tempDirectory.resolve("Test.class");
		String classPath = tempDirectory.toAbsolutePath().toString();

		classFile.writeTo(new DataOutputStream(new FileOutputStream(tempFile.toFile())));

		String output = executeClass("Test", classPath);

		assertThat(output).isEqualTo("16");
	}

	public String executeClass(String mainClass, String classPath) throws IOException {
		ProcessBuilder processBuilder = new ProcessBuilder("java", "-cp", classPath, mainClass);
		processBuilder.directory(new File(System.getProperty("java.home") + File.separator + "bin"));
		Process process = processBuilder.start();
		return new BufferedReader(new InputStreamReader(process.getInputStream())).lines().collect(joining("\n"));
	}
}
