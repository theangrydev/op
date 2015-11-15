/**
 * Copyright 2015 Liam Williams <liam.williams@zoho.com>.
 *
 * This file is part of op.
 *
 * op is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * op is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with op.  If not, see <http://www.gnu.org/licenses/>.
 */
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
