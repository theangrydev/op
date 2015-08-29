package io.github.theangrydev;

import com.googlecode.yatspec.junit.Notes;
import com.googlecode.yatspec.junit.Row;
import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.junit.Table;
import com.googlecode.yatspec.state.givenwhenthen.WithTestState;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;
import java_cup.runtime.SymbolAssert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import static java.lang.String.format;
import static java.lang.reflect.Modifier.isStatic;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

@Notes("See the op.jflex and op.cup files. The jflex file uses the Symbols constants that are generated by the cup file.")
@RunWith(SpecRunner.class)
public class ScannerTest implements WithTestState, WithAssertions {

	@Table({
		@Row({"&&", "AND"}),
		@Row({"||", "OR"}),
		@Row({"if", "IF"}),
		@Row({"else", "ELSE"}),
		@Row({"code", "CODE"}),
		@Row({"api", "API"}),
		@Row({"*", "TIMES"}),
		@Row({"+", "PLUS"}),
		@Row({"-", "MINUS"}),
		@Row({"/", "DIVIDE"}),
		@Row({";", "SEMICOLON"}),
		@Row({",", "COMMA"}),
		@Row({"(", "LEFT_PARENTHESIS"}),
		@Row({")", "RIGHT_PARENTHESIS"}),
		@Row({"==", "EQUAL_TO"}),
		@Row({"<", "LESS_THAN"}),
		@Row({">", "GREATER_THAN"}),
		@Row({"<=", "LESS_THAN_OR_EQUAL_TO"}),
		@Row({">=", "GREATER_THAN_OR_EQUAL_TO"}),
		@Row({"!=", "NOT_EQUAL_TO"}),
		@Row({":", "COLON"}),
		@Row({"=", "ASSIGNMENT"}),
		@Row({".", "DOT"})
	})
	@Test
	public void shouldScanReservedSymbols(String input, String symbolName) throws Exception {
		givenAScannerWithInput(input);
		whenTheNextTokenIsFetched();
		thenTheSymbolCodeIs(symbolName);
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
		thenTheSymbolCodeIs("IDENTIFIER");
		andTheValueIsTheString(input);
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
		thenTheSymbolCodeIs("INTEGER");
		andTheValueIsTheInteger(input);
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
		thenTheSymbolCodeIs("REAL");
		andTheValueIsTheDouble(input);
	}

	@Table({
		@Row({"\"\"", ""}),
		@Row({"\"1.34\"", "1.34"}),
		@Row({"\"if\"", "if"}),
		@Row({"\"hello\"", "hello"})
	})
	@Test
	public void shouldScanStrings(String input, String value) throws Exception {
		givenAScannerWithInput(input);
		whenTheNextTokenIsFetched();
		thenTheSymbolCodeIs("STRING");
		andTheValueIsTheString(value);
	}

	@Test
	public void shouldScanStringsWithEscapedQuotes() throws Exception {
		givenAScannerWithInput("\"\\\"\"");
		whenTheNextTokenIsFetched();
		thenTheSymbolCodeIs("STRING");
		andTheValueIsTheString("\\\"");
	}

	@Test
	public void shouldScanWhiteSpace() throws Exception {
		givenAScannerWithInput(" \n \t ");
		whenTheNextTokenIsFetched();
		thenTheSymbolCodeIs("EOF");
	}

	private static final Map<String, Integer> symbolsValuesByName;
	static {
		symbolsValuesByName = stream(Symbols.class.getDeclaredFields())
			.filter(field -> isStatic(field.getModifiers()))
			.filter(field -> field.getType() == int.class)
			.collect(toMap(Field::getName, ScannerTest::getStaticInt));
	}

	private final TestState state = new TestState();

	private Scanner scanner;
	private Symbol symbol;

	private void thenTheSymbolCodeIs(String symbolName) {
		Integer code = code(symbolName);
		state.store(symbolName, code);
		assertThat(symbol).hasSym(code);
	}

	private void andTheValueIsTheDouble(String input) {
		andTheValueIs(Double.parseDouble(input));
	}

	private void andTheValueIsTheInteger(String input) {
		andTheValueIs(Integer.parseInt(input));
	}

	private void andTheValueIsTheString(String input) {
		andTheValueIs(input);
	}

	private SymbolAssert andTheValueIs(Object input) {
		return assertThat(symbol).describedAs("Value of symbol should be '%s'", input).hasValue(input);
	}

	private static Integer getStaticInt(Field field) {
		try {
			return field.getInt(null);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Could not get static int for field=" + field, e);
		}
	}

	private Integer code(String symbolName) {
		return Optional.ofNullable(symbolsValuesByName.get(symbolName)).orElseThrow(() -> symbolNotFoundException(symbolName));
	}

	private IllegalStateException symbolNotFoundException(String symbolName) {
		return new IllegalStateException(format("There is no symbol named '%s' in %s", symbolName, Symbols.class.getSimpleName()));
	}

	private void givenAScannerWithInput(String input) {
		scanner = new Scanner(input(input), new ComplexSymbolFactory());
	}

	private void whenTheNextTokenIsFetched() throws IOException {
		symbol = scanner.next_token();
	}

	private StringReader input(String input) {
		return new StringReader(input);
	}

	@Override
	public TestState testState() {
		return state;
	}
}
