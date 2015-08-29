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
		whenTheInputIsParsed();
		thenThereShouldBeAProgramWithNoStatements();
	}

	@Test
	public void shouldParseATypeDeclarationAssignmentStatement() throws Exception {
		givenAParserWithInput("Count:Integer=6;");
		whenTheInputIsParsed();
		thenTheProgramContainsASingleStatement();
		andTheStatementIsATypeDeclarationAssignment();
		andTheAssignmentHasExistingType("Integer");
		andTheAssignmentHasTargetType("Count");
		andTheAssignedValueIsAnIntegerConstant();
		andTheIntegerConstantHasValue(6);
	}

	@Test
	public void shouldParseAnExistingTypeAssignmentStatement() throws Exception {
		givenAParserWithInput("Count=777;");
		whenTheInputIsParsed();
		thenTheProgramContainsASingleStatement();
		andTheStatementIsAnExistingTypeAssignment();
		andTheAssignmentHasTargetType("Count");
		andTheAssignedValueIsAnIntegerConstant();
		andTheIntegerConstantHasValue(777);
	}

	@Test
	public void shouldParseAStatementWithIntegerConstantAddition() throws Exception {
		givenAParserWithInput("Count=1+2;");
		whenTheInputIsParsed();
		thenTheProgramContainsASingleStatement();
		andTheStatementIsAnExistingTypeAssignment();
		andTheAssignmentHasTargetType("Count");
		andTheAssignedValueIsAnIntegerConstant();
		andTheIntegerConstantHasValue(3);
	}

	@Test
	public void shouldParseAStatementWithRealConstantAddition() throws Exception {
		givenAParserWithInput("Count=1.5+2.5;");
		whenTheInputIsParsed();
		thenTheProgramContainsASingleStatement();
		andTheStatementIsAnExistingTypeAssignment();
		andTheAssignmentHasTargetType("Count");
		andTheAssignedValueIsARealConstant();
		andTheRealConstantHasValue(4.0);
	}

	@Test
	public void shouldParseAStatementWithStringConstantAddition() throws Exception {
		givenAParserWithInput("Count=\"1.5\"+\"2.5\";");
		whenTheInputIsParsed();
		thenTheProgramContainsASingleStatement();
		andTheStatementIsAnExistingTypeAssignment();
		andTheAssignmentHasTargetType("Count");
		andTheAssignedValueIsAStringConstant();
		andTheStringConstantHasValue("1.52.5");
	}

	private static final String THE_STATEMENT = "statement";
	private static final String ASSIGNED_VALUE = "assigned value";
	private static final String THE_ROOT_SYMBOL = "root symbol";
	private static final String THE_PROGRAM = "program";
	private final TestState state = new TestState();

	private Parser parser;

	private void andTheIntegerConstantHasValue(int expected) {
		assertThat(theAssignedIntegerConstant()).hasValue(expected);
	}

	private void andTheRealConstantHasValue(double expected) {
		assertThat(theAssignedRealConstant()).hasValue(expected);
	}

	private void andTheStringConstantHasValue(String expected) {
		assertThat(theAssignedStringConstant()).hasValue(expected);
	}

	private void andTheAssignedValueIsAnIntegerConstant() {
		andTheAssignedValueIsAConstant(IntegerConstant.class);
	}

	private void andTheAssignedValueIsARealConstant() {
		andTheAssignedValueIsAConstant(RealConstant.class);
	}

	private void andTheAssignedValueIsAStringConstant() {
		andTheAssignedValueIsAConstant(StringConstant.class);
	}

	private void andTheAssignedValueIsAConstant(Class<? extends Constant<?>> constantType) {
		state.store(ASSIGNED_VALUE, theAssignmentStatement().getExpression());
		assertThat(theAssignedValue()).isInstanceOf(constantType);
	}

	private void andTheAssignmentHasTargetType(String targetType) {
		assertThat(theAssignmentStatement()).hasTargetType(targetType);
	}

	private void andTheAssignmentHasExistingType(String existingType) {
		assertThat(theTypeDeclarationAssignmentStatement()).hasExistingType(existingType);
	}

	private void thenTheProgramContainsASingleStatement() {
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

	private RealConstant theAssignedRealConstant() {
		return state.retrieve(ASSIGNED_VALUE, RealConstant.class);
	}

	private StringConstant theAssignedStringConstant() {
		return state.retrieve(ASSIGNED_VALUE, StringConstant.class);
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

	private void whenTheInputIsParsed() throws Exception {
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
