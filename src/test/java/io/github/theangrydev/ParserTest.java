package io.github.theangrydev;

import com.googlecode.yatspec.junit.SpecRunner;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.StringReader;

@RunWith(SpecRunner.class)
public class ParserTest implements WithAssertions {

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
		thenThereShouldBeASingleStatement();
		andTheStatementIsATypeDeclarationAssignment();
		andTheAssignmentHasTargetType("Count");
		andTheAssignedValueIsAConstant();
		andTheConstantHasValue(6);
	}

	private IntegerConstant integerConstant;
	private Parser parser;
	private Symbol symbol;
	private Statement statement;
	private TypeDeclarationAssignment typeDeclarationAssignment;

	private void andTheConstantHasValue(int expected) {
		assertThat(integerConstant).hasValue(expected);
	}

	private void andTheAssignedValueIsAConstant() {
		assertThat(typeDeclarationAssignment.getExpression()).isInstanceOf(IntegerConstant.class);
		integerConstant = (IntegerConstant) typeDeclarationAssignment.getExpression();
	}

	private void andTheAssignmentHasTargetType(String targetType) {
		assertThat(typeDeclarationAssignment).hasTargetType(targetType);
	}

	private void thenThereShouldBeASingleStatement() {
		assertThat(program().statements()).hasSize(1);
		statement = statement(0);
	}

	private void andTheStatementIsATypeDeclarationAssignment() {
		assertThat(statement).isInstanceOf(TypeDeclarationAssignment.class);
		typeDeclarationAssignment = (TypeDeclarationAssignment) statement;
	}

	private Statement statement(int index) {
		Program program = program();
		return program.statements().get(index);
	}

	private void thenThereShouldBeAProgramWithNoStatements() {
		assertThat(program().statements()).isEmpty();
	}

	private Program program() {
		assertThat(symbol.value).isInstanceOf(Program.class);
		return (Program) symbol.value;
	}

	private void whenParsed() throws Exception {
		symbol = parser.parse();
	}

	private void givenAParserWithInput(String input) {
		ComplexSymbolFactory symbolFactory = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(new StringReader(input), symbolFactory);
		parser = new Parser(scanner, symbolFactory);
	}
}
