package io.github.theangrydev;

import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;
import org.junit.Ignore;
import org.junit.Test;

import java.io.StringReader;

import static org.assertj.core.api.StrictAssertions.assertThat;

public class ParserTest {

	@Ignore
	@Test
	public void should() throws Exception {
		ComplexSymbolFactory symbolFactory = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(new StringReader("1;2;"), symbolFactory);
		Parser parser = new Parser(scanner, symbolFactory);

		Symbol symbol = parser.parse();
		assertThat(symbol.value).isEqualTo("111");
	}
}
