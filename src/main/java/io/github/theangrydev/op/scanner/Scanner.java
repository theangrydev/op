package io.github.theangrydev.op.scanner;

import java_cup.runtime.ComplexSymbolFactory;

import java.io.Reader;

public class Scanner extends JFlexScanner {

	public Scanner(Reader in, ComplexSymbolFactory symbolFactory) {
		super(in, symbolFactory);
	}
}
