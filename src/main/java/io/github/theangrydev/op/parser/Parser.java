package io.github.theangrydev.op.parser;

import io.github.theangrydev.op.scanner.Scanner;
import java_cup.runtime.ComplexSymbolFactory;

import java.io.Reader;
import java.io.StringReader;

public class Parser {
	private final CUPParser parser;

	public Parser(String unit, String input) {
		this.parser = parser(unit, input);
	}

	public Program parse() {
		try {
			return (Program) parser.parse().value;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static CUPParser parser(String unit, String input) {
		return parser(unit, new StringReader(input));
	}

	private static CUPParser parser(String unit, Reader input) {
		ComplexSymbolFactory symbolFactory = new ComplexSymbolFactory();
		return new CUPParser(new Scanner(unit, input, symbolFactory), symbolFactory);
	}
}
