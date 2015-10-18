package io.github.theangrydev.op.semantics;

import com.google.common.collect.Lists;
import io.github.theangrydev.op.parser.*;
import io.github.theangrydev.opper.grammar.Grammar;
import io.github.theangrydev.opper.parser.Parser;
import io.github.theangrydev.opper.parser.ParserFactory;
import io.github.theangrydev.opper.scanner.Scanner;
import io.github.theangrydev.opper.scanner.ScannerFactory;

import java.io.Reader;
import java.util.List;

import static io.github.theangrydev.op.grammar.ProgramGrammar.programGrammar;
import static io.github.theangrydev.op.parser.ProgramParserFactory.programParserFactory;
import static io.github.theangrydev.op.scanner.ProgramScannerFactory.programScannerFactory;
import static io.github.theangrydev.op.semantics.BinaryExpressionAnalyser.binaryExpression;
import static io.github.theangrydev.op.semantics.ConsumeExpressionAnalyser.consumeExpression;
import static io.github.theangrydev.op.semantics.ParseTreeNodeAnalyser.analyser;

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
		ParseTreeAnalyser<Program> programParseTreeAnalyser = programParseTreeAnalyser(grammar);
		return new ProgramSemanticAnalyserFactory(scannerFactory, parserFactory, programParseTreeAnalyser);
	}

	private static ParseTreeAnalyser<Program> programParseTreeAnalyser(Grammar grammar) {
		ParseTreeAnalyser<TypeExpression> typeExpression = analyser(consumeExpression(ParseTreeLeafAnalyser.analyser(TypeExpression::of)));

		ParseTreeAnalysers<Expression> expressions = new ParseTreeAnalysers<>();
		expressions.add(grammar.ruleByDefinition("Expression", "Integer"), analyser(consumeExpression(ParseTreeLeafAnalyser.analyser(IntegerConstant::of))));
		expressions.add(grammar.ruleByDefinition("Expression", "Real"), analyser(consumeExpression(ParseTreeLeafAnalyser.analyser(RealConstant::of))));
		expressions.add(grammar.ruleByDefinition("Expression", "String"), analyser(consumeExpression(ParseTreeLeafAnalyser.analyser(StringConstant::of))));
		expressions.add(grammar.ruleByDefinition("Expression", "TypeExpression"), analyser(consumeExpression(typeExpression)));
		expressions.add(grammar.ruleByDefinition("Expression", "Expression", "+", "Expression"), analyser(binaryExpression(Addition::add, expressions)));

		ParseTreeAnalyser<TypeDeclaration> typeDeclaration = analyser(binaryExpression(TypeDeclaration::of, typeExpression));

		ParseTreeAnalysers<Statement> statements = new ParseTreeAnalysers<>();
		statements.add(grammar.ruleByDefinition("Statement", "TypeDeclaration", "=", "Expression"), analyser(binaryExpression(TypeDeclarationAssignment::of, typeDeclaration, expressions)));
		statements.add(grammar.ruleByDefinition("Statement", "TypeExpression", "=", "Expression"), analyser(binaryExpression(ExistingTypeAssignment::of, typeExpression, expressions)));

		ParseTreeAnalyser<Statement> statementWithSemicolon = analyser(consumeExpression(statements));

		ParseTreeAnalysers<List<Statement>> statementListAnalysers = new ParseTreeAnalysers<>();
		statementListAnalysers.add(grammar.ruleByDefinition("StatementList", "StatementWithSemicolon"), analyser(consumeExpression(statementWithSemicolon, Lists::newArrayList)));
		statementListAnalysers.add(grammar.ruleByDefinition("StatementList", "StatementList", "StatementWithSemicolon"), analyser(binaryExpression(ProgramSemanticAnalyserFactory::addStatement, 0, statementListAnalysers, 1, statementWithSemicolon)));

		return analyser(consumeExpression(statementListAnalysers, Program::of));
	}

	private static List<Statement> addStatement(List<Statement> statements, Statement statement) {
		statements.add(statement);
		return statements;
	}
}
