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
package io.github.theangrydev.op.semantics;

import com.googlecode.yatspec.junit.SpecRunner;
import io.github.theangrydev.op.common.TestState;
import io.github.theangrydev.op.common.WithAssertions;
import io.github.theangrydev.op.common.WithTestState;
import io.github.theangrydev.op.language.Program;
import io.github.theangrydev.op.language.Statement;
import io.github.theangrydev.op.language.assignment.Assignment;
import io.github.theangrydev.op.language.assignment.ExistingTypeAssignment;
import io.github.theangrydev.op.language.assignment.TypeDeclarationAssignment;
import io.github.theangrydev.op.language.expression.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.StringReader;

import static io.github.theangrydev.op.parser.ProgramSemanticAnalyserFactory.PROGRAM_SEMANTIC_ANALYSER_FACTORY;


@RunWith(SpecRunner.class)
public class ProgramParserTest implements WithAssertions, WithTestState {

	@Test
	public void shouldParseAnEmptyProgram() throws Exception {
		givenAParserWithInput("");
		whenTheInputIsParsed();
		thenThereShouldBeAProgramWithNoStatements();
	}

	@Test
	public void shouldSimplifyIntegerConstants() throws Exception {
		givenAParserWithInput("Count:Integer=6+4+2;");
		whenTheInputIsParsed();
		thenTheProgramContainsASingleStatement();
		andTheStatementIsATypeDeclarationAssignment();
		andTheAssignmentHasExistingType("Integer");
		andTheAssignmentHasTargetType("Count");
		andTheAssignedValueIsAnIntegerConstant();
		andTheIntegerConstantHasValue(12);
	}

	@Test
	public void shouldUpCastIntegerToReal() throws Exception {
		givenAParserWithInput("Count:Real=6+4.2;");
		whenTheInputIsParsed();
		thenTheProgramContainsASingleStatement();
		andTheStatementIsATypeDeclarationAssignment();
		andTheAssignmentHasExistingType("Real");
		andTheAssignmentHasTargetType("Count");
		andTheAssignedValueIsARealConstant();
		andTheRealConstantHasValue(10.2);
	}

	@Test
	public void shouldUpCastIntegerToString() throws Exception {
		givenAParserWithInput("Count:String=\"hello\"+4;");
		whenTheInputIsParsed();
		thenTheProgramContainsASingleStatement();
		andTheStatementIsATypeDeclarationAssignment();
		andTheAssignmentHasExistingType("String");
		andTheAssignmentHasTargetType("Count");
		andTheAssignedValueIsAStringConstant();
		andTheStringConstantHasValue("hello4");
	}

	@Test
	public void shouldUpCastRealToString() throws Exception {
		givenAParserWithInput("Count:String=\"hello\"+4.2;");
		whenTheInputIsParsed();
		thenTheProgramContainsASingleStatement();
		andTheStatementIsATypeDeclarationAssignment();
		andTheAssignmentHasExistingType("String");
		andTheAssignmentHasTargetType("Count");
		andTheAssignedValueIsAStringConstant();
		andTheStringConstantHasValue("hello4.2");
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
		state.store(THE_PROGRAM,  programParser.analyse().get().simplify());
	}

	private void givenAParserWithInput(String input) throws IOException {
		programParser = PROGRAM_SEMANTIC_ANALYSER_FACTORY.programSemanticAnalyser(new StringReader(input));
	}

	@Override
	public TestState testState() {
		return state;
	}
}
