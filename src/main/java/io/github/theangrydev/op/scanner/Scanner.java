package io.github.theangrydev.op.scanner;

import java_cup.runtime.ComplexSymbolFactory;

import java.io.Reader;

public class Scanner extends JFlexScanner {

	public Scanner(String unit, Reader input, ComplexSymbolFactory symbolFactory) {
		super(input, unit, symbolFactory);
	}
}
