package io.github.theangrydev.op.generation;

import io.github.theangrydev.op.language.Program;
import org.junit.Test;

import java.io.StringReader;

import static io.github.theangrydev.op.parser.ProgramSemanticAnalyserFactory.PROGRAM_SEMANTIC_ANALYSER_FACTORY;

public class GenerationTest {

	private final ProgramCompiler programCompiler = new ProgramCompiler() {

		private final SymbolTable symbolTable = new SymbolTable(new VariableReferences());

		@Override
		public void addTwoIntegers() {
			System.out.println("addTwoIntegers");
		}

		@Override
		public void addTwoReals() {
			System.out.println("addTwoReals");
		}

		@Override
		public void addTwoStrings() {
			System.out.println("addTwoStrings");
		}

		@Override
		public void populateDefaultTypes() {
			System.out.println("populateDefaultTypes");
			symbolTable.populateDefaultTypes();
		}

		@Override
		public ConstantReference registerIntegerConstant(int value) {
			System.out.println("registerIntegerConstant(" + value + ")");
			return symbolTable.registerIntegerConstant();
		}

		@Override
		public ConstantReference registerRealConstant(double value) {
			System.out.println("registerRealConstant(" + value + ")");
			return symbolTable.registerRealConstant();
		}

		@Override
		public ConstantReference registerStringConstant(String value) {
			System.out.println("registerStringConstant(" + value + ")");
			return symbolTable.registerStringConstant();
		}

		@Override
		public VariableReference registerVariableReference(String targetTypeName, String existingTypeName) {
			System.out.println("registerVariableReference(" + targetTypeName + "," + existingTypeName + ")");
			return symbolTable.registerVariableReference(targetTypeName, existingTypeName);
		}

		@Override
		public VariableReference lookupVariableReference(String typeName) {
			System.out.println("lookupVariableReference(" + typeName + ")");
			return symbolTable.lookupVariableReference(typeName);
		}

		@Override
		public void storeInteger(TypeReference typeToStoreIn) {
			System.out.println("storeInteger(" + typeToStoreIn + ")");
		}

		@Override
		public void storeReal(TypeReference typeToStoreIn) {
			System.out.println("storeReal(" + typeToStoreIn + ")");
		}

		@Override
		public void storeString(TypeReference typeToStoreIn) {
			System.out.println("storeString(" + typeToStoreIn + ")");
		}

		@Override
		public void storeReference(TypeReference typeToStoreIn) {
			System.out.println("storeReference(" + typeToStoreIn + ")");
		}

		@Override
		public void loadIntegerFromConstant(ConstantReference constantToLoad) {
			System.out.println("loadIntegerFromConstant(" + constantToLoad + ")");
		}

		@Override
		public void loadIntegerFromVariable(VariableReference variableToLoad) {
			System.out.println("loadIntegerVariable(" + variableToLoad + ")");
		}

		@Override
		public void loadRealFromConstant(ConstantReference constantToLoad) {
			System.out.println("loadRealFromConstant(" + constantToLoad + ")");
		}

		@Override
		public void loadRealFromVariable(VariableReference variableToLoad) {
			System.out.println("loadRealFromVariable(" + variableToLoad + ")");
		}

		@Override
		public void loadStringFromConstant(ConstantReference constantToLoad) {
			System.out.println("loadStringFromConstant(" + constantToLoad + ")");
		}

		@Override
		public void loadStringFromVariable(VariableReference variableToLoad) {
			System.out.println("loadStringFromVariable(" + variableToLoad + ")");
		}

		@Override
		public void loadReferenceFromConstant(ConstantReference constantToLoad) {
			System.out.println("loadReferenceFromConstant(" + constantToLoad + ")");
		}

		@Override
		public void loadReferenceFromVariable(VariableReference variableToLoad) {
			System.out.println("loadReferenceFromVariable(" + variableToLoad + ")");
		}
	};

	@Test
	public void shouldGenerateCode() {
		Program program = PROGRAM_SEMANTIC_ANALYSER_FACTORY.programSemanticAnalyser(new StringReader("Count:Integer=6+4+2;A:Count=Count+Count;")).analyse().get();
		program.simplify();
		program.checkTypes(programCompiler);
		program.compile(programCompiler);
	}
}
