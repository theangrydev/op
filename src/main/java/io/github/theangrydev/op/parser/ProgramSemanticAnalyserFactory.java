package io.github.theangrydev.op.parser;

import io.github.theangrydev.op.language.Program;
import io.github.theangrydev.op.semantics.ParseTreeAnalyser;
import io.github.theangrydev.op.semantics.SemanticAnalyser;
import io.github.theangrydev.opper.parser.Parser;
import io.github.theangrydev.opper.parser.ParserFactory;
import io.github.theangrydev.opper.scanner.Scanner;
import io.github.theangrydev.opper.scanner.ScannerFactory;

import java.io.Reader;

import static io.github.theangrydev.op.parser.ProgramParseTreeAnalyserFactory.PROGRAM_PARSE_TREE_ANALYSER_FACTORY;
import static io.github.theangrydev.op.parser.ProgramParserFactory.PROGRAM_PARSER_FACTORY;
import static io.github.theangrydev.op.parser.ProgramScannerFactory.PROGRAM_SCANNER_FACTORY;

public class ProgramSemanticAnalyserFactory {
	public static ProgramSemanticAnalyserFactory PROGRAM_SEMANTIC_ANALYSER_FACTORY = programAnalyserFactory();

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

	private static ProgramSemanticAnalyserFactory programAnalyserFactory() {
		return new ProgramSemanticAnalyserFactory(PROGRAM_SCANNER_FACTORY, PROGRAM_PARSER_FACTORY, PROGRAM_PARSE_TREE_ANALYSER_FACTORY);
	}
}
