package io.github.theangrydev;

import com.googlecode.yatspec.junit.SpecRunner;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.StringReader;

@RunWith(SpecRunner.class)
public class ParserTest implements WithAssertions, WithTestState {

	@Test
	public void shouldParseAnEmptyStatement() throws Exception {
		givenAParserWithInput("");
		whenParsed();
		thenThereShouldBeAProgramWithNoStatements();
	}

	@Test
	public void shouldParseATypeDeclarationAssignmentStatement() throws Exception {
		givenAParserWithInput("Count:Integer=6;");
		whenParsed();
		thenTheProgramShouldContainASingleStatement();
		andTheStatementIsATypeDeclarationAssignment();
		andTheAssignmentHasExistingType("Integer");
		andTheAssignmentHasTargetType("Count");
		andTheAssignedValueIsAConstant();
		andTheConstantHasValue(6);
	}

	@Test
	public void shouldParseAnExistingTypeAssignmentStatement() throws Exception {
		givenAParserWithInput("Count=777;");
		whenParsed();
		thenTheProgramShouldContainASingleStatement();
		andTheStatementIsAnExistingTypeAssignment();
		andTheAssignmentHasTargetType("Count");
		andTheAssignedValueIsAConstant();
		andTheConstantHasValue(777);
	}

	private static final String THE_STATEMENT = "statement";

	private static final String ASSIGNED_VALUE = "assigned value";
	private static final String THE_ROOT_SYMBOL = "root symbol";
	private static final String THE_PROGRAM = "program";
	private final TestState state = new TestState();

	private Parser parser;

	private void andTheConstantHasValue(int expected) {
		assertThat(theAssignedIntegerConstant()).hasValue(expected);
	}

	private void andTheAssignedValueIsAConstant() {
		state.store(ASSIGNED_VALUE, theAssignmentStatement().getExpression());
		assertThat(theAssignedValue()).isInstanceOf(IntegerConstant.class);
	}

	private void andTheAssignmentHasTargetType(String targetType) {
		assertThat(theAssignmentStatement()).hasTargetType(targetType);
	}

	private void andTheAssignmentHasExistingType(String existingType) {
		assertThat(theTypeDeclarationAssignmentStatement()).hasExistingType(existingType);
	}

	private void thenTheProgramShouldContainASingleStatement() {
		thenTheRootSymbolShouldBeAProgram();
		assertThat(theProgram().statements()).hasSize(1);
		state.store(THE_STATEMENT, statement(0));
	}

	private void andTheStatementIsAnExistingTypeAssignment() {
		assertThat(theStatement()).isInstanceOf(ExistingTypeAssignment.class);
	}

	private void andTheStatementIsATypeDeclarationAssignment() {
		assertThat(theStatement()).isInstanceOf(TypeDeclarationAssignment.class);
	}

	private Expression theAssignedValue() {
		return state.retrieve(ASSIGNED_VALUE, Expression.class);
	}

	private IntegerConstant theAssignedIntegerConstant() {
		return state.retrieve(ASSIGNED_VALUE, IntegerConstant.class);
	}

	private TypeDeclarationAssignment theTypeDeclarationAssignmentStatement() {
		return state.retrieve(THE_STATEMENT, TypeDeclarationAssignment.class);
	}

	private Assignment theAssignmentStatement() {
		return state.retrieve(THE_STATEMENT, Assignment.class);
	}

	private Statement theStatement() {
		return state.retrieve(THE_STATEMENT, Statement.class);
	}

	private Symbol theRootSymbol() {
		return state.retrieve(THE_ROOT_SYMBOL, Symbol.class);
	}

	private Statement statement(int index) {
		return theProgram().statements().get(index);
	}

	private void thenThereShouldBeAProgramWithNoStatements() {
		thenTheRootSymbolShouldBeAProgram();
		assertThat(theProgram().statements()).isEmpty();
	}

	private Program theProgram() {
		return state.retrieve(THE_PROGRAM, Program.class);
	}

	private void thenTheRootSymbolShouldBeAProgram() {
		Object theRootSymbolValue = theRootSymbol().value;
		assertThat(theRootSymbolValue).isInstanceOf(Program.class);
		state.store(THE_PROGRAM, theRootSymbolValue);
	}

	private void whenParsed() throws Exception {
		state.store(THE_ROOT_SYMBOL,  parser.parse());
	}

	private void givenAParserWithInput(String input) {
		ComplexSymbolFactory symbolFactory = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(new StringReader(input), symbolFactory);
		parser = new Parser(scanner, symbolFactory);
	}

	@Override
	public TestState testState() {
		return state;
	}
}
