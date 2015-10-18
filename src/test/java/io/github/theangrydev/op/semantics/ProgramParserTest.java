package io.github.theangrydev.op.semantics;

import com.googlecode.yatspec.junit.SpecRunner;
import io.github.theangrydev.op.common.TestState;
import io.github.theangrydev.op.common.WithAssertions;
import io.github.theangrydev.op.common.WithTestState;
import io.github.theangrydev.op.language.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.StringReader;

import static io.github.theangrydev.op.parser.ProgramSemanticAnalyserFactory.programAnalyserFactory;

@RunWith(SpecRunner.class)
public class ProgramParserTest implements WithAssertions, WithTestState {

	@Ignore //TODO: support nullable rule definitions
	@Test
	public void shouldParseAnEmptyProgram() throws Exception {
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
	public void shouldParseAStatementWithAnInteger() throws Exception {
		givenAParserWithInput("Count=123;");
		whenTheInputIsParsed();
		thenTheProgramContainsASingleAssignmentFor("Count");
		andTheAssignedValueIsAnIntegerConstant();
		andTheIntegerConstantHasValue(123);
	}

	@Test
	public void shouldParseAStatementWithAReal() throws Exception {
		givenAParserWithInput("Count=7.5;");
		whenTheInputIsParsed();
		thenTheProgramContainsASingleAssignmentFor("Count");
		andTheAssignedValueIsARealConstant();
		andTheRealConstantHasValue(7.5);
	}

	@Test
	public void shouldParseAStatementWithAString() throws Exception {
		givenAParserWithInput("Count=\"Hello World!\";");
		whenTheInputIsParsed();
		thenTheProgramContainsASingleAssignmentFor("Count");
		andTheAssignedValueIsAStringConstant();
		andTheStringConstantHasValue("Hello World!");
	}

	@Test
	public void shouldParseAStatementWithAddition() throws Exception {
		givenAParserWithInput("Count=A+7;");
		whenTheInputIsParsed();
		thenTheProgramContainsASingleAssignmentFor("Count");
		andTheAssignedValueIsAnAddition();
		andTheAdditionHasLeftOperand("A");
		andTheAdditionHasRightOperand(7);
	}

	private void andTheAdditionHasLeftOperand(String type) {
		assertThat((TypeExpression) theAssignedTypeAddition().getLeft()).hasType(type);
	}

	private void andTheAdditionHasRightOperand(int integer) {
		assertThat((IntegerConstant) theAssignedTypeAddition().getRight()).hasValue(integer);
	}

	private static final String THE_STATEMENT = "statement";
	private static final String ASSIGNED_VALUE = "assigned value";
	private static final String THE_PROGRAM = "program";
	private final TestState state = new TestState();
	private SemanticAnalyser<Program> programParser;

	private void andTheIntegerConstantHasValue(int expected) {
		assertThat(theAssignedIntegerConstant()).hasValue(expected);
	}

	private void andTheRealConstantHasValue(double expected) {
		assertThat(theAssignedRealConstant()).hasValue(expected);
	}

	private void andTheStringConstantHasValue(String expected) {
		assertThat(theAssignedStringConstant()).hasValue(expected);
	}

	private void andTheAssignedValueIsAnAddition() {
		storeTheAssignedValue();
		assertThat(theAssignedValue()).isExactlyInstanceOf(Addition.class);
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
		assertThat(theAssignmentStatement().getTargetType()).hasType(targetType);
	}

	private void andTheAssignmentHasExistingType(String existingType) {
		assertThat(theTypeDeclarationAssignmentStatement().getExistingType()).hasType(existingType);
	}

	private void thenTheProgramContainsASingleStatement() {
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

	private Addition theAssignedTypeAddition() {
		return theAssignedValue(Addition.class);
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

	private Statement statement(int index) {
		return theProgram().statements().get(index);
	}

	private void thenThereShouldBeAProgramWithNoStatements() {
		assertThat(theProgram().statements()).isEmpty();
	}

	private Program theProgram() {
		return state.retrieve(THE_PROGRAM, Program.class);
	}

	private void whenTheInputIsParsed() throws Exception {
		state.store(THE_PROGRAM,  programParser.analyse().get());
	}

	private void givenAParserWithInput(String input) {
		programParser = programAnalyserFactory().programSemanticAnalyser(new StringReader(input));
	}

	@Override
	public TestState testState() {
		return state;
	}
}
