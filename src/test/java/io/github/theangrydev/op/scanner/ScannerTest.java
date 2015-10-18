package io.github.theangrydev.op.scanner;

import com.googlecode.yatspec.junit.Row;
import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.junit.Table;
import com.googlecode.yatspec.state.givenwhenthen.WithTestState;
import io.github.theangrydev.TestState;
import io.github.theangrydev.WithAssertions;
import io.github.theangrydev.opper.grammar.GrammarBuilder;
import io.github.theangrydev.opper.scanner.ScannedSymbol;
import io.github.theangrydev.opper.scanner.Scanner;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.StringReader;

@RunWith(SpecRunner.class)
public class ScannerTest implements WithTestState, WithAssertions {

	@Table({
		@Row("&&"),
		@Row("||"),
		@Row("if"),
		@Row("else"),
		@Row("code"),
		@Row("api"),
		@Row("*"),
		@Row("+"),
		@Row("-"),
		@Row("/"),
		@Row(";"),
		@Row(","),
		@Row("("),
		@Row(")"),
		@Row("=="),
		@Row("<"),
		@Row(">"),
		@Row("<="),
		@Row(">="),
		@Row("!="),
		@Row(":"),
		@Row("="),
		@Row(".")
	})
	@Test
	public void shouldScanReservedSymbols(String input) throws Exception {
		System.out.println("Scanning=" +input);
		givenAScannerWithInput(input);
		whenTheNextTokenIsFetched();
		thenTheSymbolCodeIs(input);
	}

	@Table({
		@Row({"Identifier"}),
		@Row({"AIdentifier"}),
		@Row({"BIdentifier0"}),
		@Row({"CIdenTifiEr"})
	})
	@Test
	public void shouldScanIdentifiers(String input) throws Exception {
		givenAScannerWithInput(input);
		whenTheNextTokenIsFetched();
		thenTheSymbolCodeIs("Identifier");
		andTheValueIs(input);
	}

	@Table({
		@Row({"1234"}),
		@Row({"01234"}),
		@Row({"1"}),
		@Row({"0"})
	})
	@Test
	public void shouldScanIntegers(String input) throws Exception {
		givenAScannerWithInput(input);
		whenTheNextTokenIsFetched();
		thenTheSymbolCodeIs("Integer");
		andTheValueIs(input);
	}

	@Table({
		@Row({"0.1234"}),
		@Row({"1.34"}),
		@Row({"100.0"}),
		@Row({"00.00"})
	})
	@Test
	public void shouldScanReals(String input) throws Exception {
		givenAScannerWithInput(input);
		whenTheNextTokenIsFetched();
		thenTheSymbolCodeIs("Real");
		andTheValueIs(input);
	}

	@Ignore //TODO: put string literals in the test grammar
	@Table({
		@Row({"\"\"", ""}),
		@Row({"\"1.34\"", "1.34"}),
		@Row({"\"if\"", "if"}),
		@Row({"\"hello\"", "hello"}),
		@Row({"\"\\\"\"", "\\\""}),
		@Row({"\"'\n\r\t'\"'", "'\n\r\t'"})
	})
	@Test
	public void shouldScanStrings(String input, String value) throws Exception {
		givenAScannerWithInput(input);
		whenTheNextTokenIsFetched();
		thenTheSymbolCodeIs("String");
		andTheValueIs(value);
	}

	@Test
	public void shouldScanWhiteSpace() throws Exception {
		givenAScannerWithInput(" \n \t ");
		whenTheNextTokenIsFetched();
		thenTheSymbolCodeIs("Whitespace");
	}

	private TestState state = new TestState();

	private Scanner scanner;
	private ScannedSymbol symbol;

	private void thenTheSymbolCodeIs(String symbolName) {
		String code = symbol.symbol().toString();
		state.store(symbolName, code);
		assertThat(code).isEqualTo(symbolName);
	}

	private void andTheValueIs(String input) {
		assertThat(symbol.content()).describedAs("Value of symbol should be '%s'", input).isEqualTo(input);
	}

	private void givenAScannerWithInput(String input) {
		scanner = new ScannerFactory(new GrammarBuilder()).scanner(new StringReader(input));
	}

	private void whenTheNextTokenIsFetched() throws IOException {
		if (scanner.hasNextSymbol()) {
			symbol = scanner.nextSymbol();
		}
	}

	@Override
	public TestState testState() {
		return state;
	}
}
