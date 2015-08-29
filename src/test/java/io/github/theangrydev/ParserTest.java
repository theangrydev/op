package io.github.theangrydev;

import com.googlecode.yatspec.junit.SpecRunner;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;
import org.assertj.core.api.WithAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.StringReader;

@RunWith(SpecRunner.class)
public class ParserTest implements WithAssertions {

	private Parser parser;
	private Symbol symbol;

	@Test
	public void shouldParseAnEmptyStatement() throws Exception {
		givenAParserWithInput(";");
		whenParsed();
		thereShouldBeAProgramWithNoStatements();
	}

	private void thereShouldBeAProgramWithNoStatements() {
		assertThat(symbol.value).isInstanceOf(Program.class);
		Program program = (Program) symbol.value;
		assertThat(program.statements()).isEmpty();
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
