package io.github.theangrydev.op.parser;

import io.github.theangrydev.op.scanner.Scanner;
import java_cup.runtime.ComplexSymbolFactory;

import java.io.Reader;
import java.io.StringReader;

public class Parser {
	private final CUPParser parser;

	public Parser(String input) {
		parser = parser(input);
	}

	public Program parse() {
		try {
			return (Program) parser.parse().value;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static CUPParser parser(String input) {
		return parser(new StringReader(input));
	}

	private static CUPParser parser(Reader input) {
		ComplexSymbolFactory symbolFactory = new ComplexSymbolFactory();
		return new CUPParser(new Scanner(input, symbolFactory), symbolFactory);
	}
}
