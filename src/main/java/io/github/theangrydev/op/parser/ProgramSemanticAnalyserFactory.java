package io.github.theangrydev.op.parser;

import io.github.theangrydev.op.language.Program;
import io.github.theangrydev.op.semantics.ParseTreeAnalyser;
import io.github.theangrydev.op.semantics.SemanticAnalyser;
import io.github.theangrydev.opper.grammar.Grammar;
import io.github.theangrydev.opper.parser.Parser;
import io.github.theangrydev.opper.parser.ParserFactory;
import io.github.theangrydev.opper.scanner.Scanner;
import io.github.theangrydev.opper.scanner.ScannerFactory;

import java.io.Reader;

import static io.github.theangrydev.op.parser.ProgramGrammar.programGrammar;
import static io.github.theangrydev.op.parser.ProgramParserFactory.programParserFactory;
import static io.github.theangrydev.op.parser.ProgramScannerFactory.programScannerFactory;

public class ProgramSemanticAnalyserFactory {

	private final ScannerFactory scannerFactory;
	private final ParserFactory parserFactory;
	private final ParseTreeAnalyser<Program> programParseTreeAnalyser;

	private ProgramSemanticAnalyserFactory(ScannerFactory scannerFactory, ParserFactory parserFactory, ParseTreeAnalyser<Program> programParseTreeAnalyser) {
		this.programParseTreeAnalyser = programParseTreeAnalyser;
		this.scannerFactory = scannerFactory;
		this.parserFactory = parserFactory;
	}

	public SemanticAnalyser<Program> programSemanticAnalyser(Reader charactersToParse) {
		Scanner scanner = scannerFactory.scanner(charactersToParse);
		Parser parser = parserFactory.parser(scanner);
		return new SemanticAnalyser<>(parser, programParseTreeAnalyser);
	}

	public static ProgramSemanticAnalyserFactory programAnalyserFactory() {
		Grammar grammar = programGrammar();
		ScannerFactory scannerFactory = programScannerFactory(grammar);
		ParserFactory parserFactory = programParserFactory(grammar);
		ParseTreeAnalyser<Program> programParseTreeAnalyser = ProgramParseTreeAnalyserFactory.programParseTreeAnalyser(grammar);
		return new ProgramSemanticAnalyserFactory(scannerFactory, parserFactory, programParseTreeAnalyser);
	}
}
