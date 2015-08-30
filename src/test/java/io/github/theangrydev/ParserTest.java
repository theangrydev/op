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
	public void shouldParseAStatementWithTypeAndIntegerConstantAddition() throws Exception {
		givenAParserWithInput("Count=A+7;");
		whenTheInputIsParsed();
		thenTheProgramContainsASingleAssignmentFor("Count");
		andTheAssignedValueIsAnIntegerAddition();
		andTheIntegerAdditionHasATypeOnTheLeft("A");
		andTheIntegerAdditionHasAnIntegerConstantOnTheRight(7);
	}

	@Test
	public void shouldParseAStatementWithTypeAndTypeAddition() throws Exception {
		givenAParserWithInput("Count=A+B;");
		whenTheInputIsParsed();
		thenTheProgramContainsASingleAssignmentFor("Count");
		andTheAssignedValueIsATypeAddition();
		andTheTypeAdditionHasATypeOnTheLeft("A");
		andTheTypeAdditionHasATypeOnTheRight("B");
	}

	@Test
	public void shouldParseAStatementWithIntegerConstantAddition() throws Exception {
		givenAParserWithInput("Count=1+2;");
		whenTheInputIsParsed();
		thenTheProgramContainsASingleAssignmentFor("Count");
		andTheAssignedValueIsAnIntegerConstant();
		andTheIntegerConstantHasValue(3);
	}

	@Test
	public void shouldParseAStatementWithItegerConstantAddition() throws Exception {
		givenAParserWithInput("Count=1+2;");
		whenTheInputIsParsed();
		thenTheProgramContainsASingleAssignmentFor("Count");
		andTheAssignedValueIsAnIntegerConstant();
		andTheIntegerConstantHasValue(3);
	}

	@Test
	public void shouldParseAStatementWithRealConstantAddition() throws Exception {
		givenAParserWithInput("Count=1.5+2.5;");
		whenTheInputIsParsed();
		thenTheProgramContainsASingleAssignmentFor("Count");
		andTheAssignedValueIsARealConstant();
		andTheRealConstantHasValue(4.0);
	}

	@Test
	public void shouldParseAStatementWithIntegerConstantSubtraction() throws Exception {
		givenAParserWithInput("Count=1-2;");
		whenTheInputIsParsed();
		thenTheProgramContainsASingleAssignmentFor("Count");
		andTheAssignedValueIsAnIntegerConstant();
		andTheIntegerConstantHasValue(-1);
	}

	@Test
	public void shouldParseAStatementWithRealConstantSubtraction() throws Exception {
		givenAParserWithInput("Count=1.5-2.5;");
		whenTheInputIsParsed();
		thenTheProgramContainsASingleAssignmentFor("Count");
		andTheAssignedValueIsARealConstant();
		andTheRealConstantHasValue(-1.0);
	}

	@Test
	public void shouldParseAStatementWithIntegerConstantMultiplication() throws Exception {
		givenAParserWithInput("Count=3*2;");
		whenTheInputIsParsed();
		thenTheProgramContainsASingleAssignmentFor("Count");
		andTheAssignedValueIsAnIntegerConstant();
		andTheIntegerConstantHasValue(6);
	}

	@Test
	public void shouldParseAStatementWithRealConstantMultiplication() throws Exception {
		givenAParserWithInput("Count=1.5*2.0;");
		whenTheInputIsParsed();
		thenTheProgramContainsASingleAssignmentFor("Count");
		andTheAssignedValueIsARealConstant();
		andTheRealConstantHasValue(3);
	}

	@Test
	public void shouldParseAStatementWithIntegerConstantDivision() throws Exception {
		givenAParserWithInput("Count=10/2;");
		whenTheInputIsParsed();
		thenTheProgramContainsASingleAssignmentFor("Count");
		andTheAssignedValueIsAnIntegerConstant();
		andTheIntegerConstantHasValue(5);
	}

	@Test
	public void shouldParseAStatementWithRealConstantDivision() throws Exception {
		givenAParserWithInput("Count=1.0/2.0;");
		whenTheInputIsParsed();
		thenTheProgramContainsASingleAssignmentFor("Count");
		andTheAssignedValueIsARealConstant();
		andTheRealConstantHasValue(0.5);
	}

	@Test
	public void shouldParseAStatementWithStringConstantAddition() throws Exception {
		givenAParserWithInput("Count=\"1.5\"+\"2.5\";");
		whenTheInputIsParsed();
		thenTheProgramContainsASingleAssignmentFor("Count");
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

	private void andTheAssignedValueIsAnIntegerAddition() {
		storeTheAssignedValue();
		assertThat(theAssignedValue()).isExactlyInstanceOf(IntegerAddition.class);
	}

	private void andTheAssignedValueIsATypeAddition() {
		storeTheAssignedValue();
		assertThat(theAssignedValue()).isExactlyInstanceOf(TypeAddition.class);
	}

	private void andTheIntegerAdditionHasAnIntegerConstantOnTheRight(int constant) {
		assertThat((IntegerConstant) theAssignedIntegerAddition().getRight()).hasValue(constant);
	}

	private void andTheTypeAdditionHasATypeOnTheLeft(String type) {
		assertThat((TypeExpression<Object>) theAssignedTypeAddition().getLeft()).hasType(type);
	}

	private void andTheTypeAdditionHasATypeOnTheRight(String type) {
		assertThat((TypeExpression<Object>) theAssignedTypeAddition().getRight()).hasType(type);
	}

	private void andTheIntegerAdditionHasATypeOnTheLeft(String type) {
		assertThat((TypeExpression<Integer>) theAssignedIntegerAddition().getLeft()).hasType(type);
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
		storeTheAssignedValue();
		assertThat(theAssignedValue()).isExactlyInstanceOf(constantType);
	}

	private void storeTheAssignedValue() {
		state.store(ASSIGNED_VALUE, theAssignmentStatement().getExpression());
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

	private void thenTheProgramContainsASingleAssignmentFor(String count) {
		thenTheProgramContainsASingleStatement();
		andTheStatementIsAnExistingTypeAssignment();
		andTheAssignmentHasTargetType(count);
	}

	private void andTheStatementIsAnExistingTypeAssignment() {
		assertThat(theStatement()).isExactlyInstanceOf(ExistingTypeAssignment.class);
	}

	private void andTheStatementIsATypeDeclarationAssignment() {
		assertThat(theStatement()).isExactlyInstanceOf(TypeDeclarationAssignment.class);
	}

	private Expression theAssignedValue() {
		return theAssignedValue(Expression.class);
	}

	private TypeAddition theAssignedTypeAddition() {
		return theAssignedValue(TypeAddition.class);
	}

	private IntegerAddition theAssignedIntegerAddition() {
		return theAssignedValue(IntegerAddition.class);
	}

	private IntegerConstant theAssignedIntegerConstant() {
		return theAssignedValue(IntegerConstant.class);
	}

	private RealConstant theAssignedRealConstant() {
		return theAssignedValue(RealConstant.class);
	}

	private StringConstant theAssignedStringConstant() {
		return theAssignedValue(StringConstant.class);
	}

	private <T> T theAssignedValue(Class<T> type) {
		return state.retrieve(ASSIGNED_VALUE, type);
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
		assertThat(theRootSymbolValue).isExactlyInstanceOf(Program.class);
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
