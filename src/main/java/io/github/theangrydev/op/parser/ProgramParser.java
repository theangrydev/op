package io.github.theangrydev.op.parser;

import com.google.common.collect.Lists;
import io.github.theangrydev.op.scanner.ScannerFactory;
import io.github.theangrydev.op.semantics.ParseTreeAnalyser;
import io.github.theangrydev.op.semantics.ParseTreeAnalysers;
import io.github.theangrydev.op.semantics.SemanticAnalyser;
import io.github.theangrydev.opper.common.SystemOutLogger;
import io.github.theangrydev.opper.grammar.GrammarBuilder;
import io.github.theangrydev.opper.parser.EarlyParser;
import io.github.theangrydev.opper.scanner.Scanner;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import static io.github.theangrydev.op.semantics.BinaryExpressionAnalyser.binaryExpression;
import static io.github.theangrydev.op.semantics.ConsumeExpressionAnalyser.consumeExpression;
import static io.github.theangrydev.op.semantics.ParseTreeLeafAnalyser.analyser;
import static io.github.theangrydev.op.semantics.ParseTreeNodeAnalyser.analyser;

public class ProgramParser {
	private final SemanticAnalyser<Program> parseTreeAnalyser;

	public ProgramParser(String input) {
		this.parseTreeAnalyser = parser(input);
	}

	public Program parse() {
		return parseTreeAnalyser.analyse().get();
	}

	private static SemanticAnalyser<Program> parser(String input) {
		return parser(new StringReader(input));
	}

	private static SemanticAnalyser<Program> parser(Reader input) {
		GrammarBuilder grammar = new GrammarBuilder()
			.withAcceptanceSymbol("Accept")
			.withStartSymbol("Program")
			.withRule("Program", "StatementList")
			.withRule("StatementList", "StatementList", "StatementWithSemicolon")
			.withRule("StatementList", "StatementWithSemicolon")
			.withRule("StatementWithSemicolon", "Statement", ";")
			.withRule("Statement", "TypeDeclaration", "=", "Expression")
			.withRule("Statement", "TypeExpression", "=", "Expression")
			.withRule("TypeDeclaration", "TypeExpression", ":", "TypeExpression")
			.withRule("TypeExpression", "Identifier")
			.withRule("Expression", "Integer")
			.withRule("Expression", "Real")
			.withRule("Expression", "TypeExpression")
			.withRule("Expression", "Expression", "+", "Expression");

		ParseTreeAnalyser<TypeExpression> typeExpression = analyser(consumeExpression(analyser(TypeExpression::of)));

		ParseTreeAnalysers<Expression> expressions = new ParseTreeAnalysers<>();
		expressions.add(grammar.ruleByDefinition("Expression", "Integer"), analyser(consumeExpression(analyser(IntegerConstant::of))));
		expressions.add(grammar.ruleByDefinition("Expression", "Real"), analyser(consumeExpression(analyser(RealConstant::of))));
		expressions.add(grammar.ruleByDefinition("Expression", "TypeExpression"), analyser(consumeExpression(typeExpression)));
		expressions.add(grammar.ruleByDefinition("Expression", "Expression", "+", "Expression"), analyser(binaryExpression(Addition::add, expressions)));

		ParseTreeAnalyser<TypeDeclaration> typeDeclaration = analyser(binaryExpression(TypeDeclaration::of, typeExpression));

		ParseTreeAnalysers<Statement> statements = new ParseTreeAnalysers<>();
		statements.add(grammar.ruleByDefinition("Statement", "TypeDeclaration", "=", "Expression"), analyser(binaryExpression(TypeDeclarationAssignment::of, typeDeclaration, expressions)));
		statements.add(grammar.ruleByDefinition("Statement", "TypeExpression", "=", "Expression"), analyser(binaryExpression(ExistingTypeAssignment::of, typeExpression, expressions)));

		ParseTreeAnalyser<Statement> statementWithSemicolon = analyser(consumeExpression(statements));

		ParseTreeAnalysers<List<Statement>> statementListAnalysers = new ParseTreeAnalysers<>();
		statementListAnalysers.add(grammar.ruleByDefinition("StatementList", "StatementWithSemicolon"), analyser(consumeExpression(statementWithSemicolon, Lists::newArrayList)));
		statementListAnalysers.add(grammar.ruleByDefinition("StatementList", "StatementList", "StatementWithSemicolon"), analyser(binaryExpression(ProgramParser::addStatement, 0, statementListAnalysers, 1, statementWithSemicolon)));

		Scanner scanner = new ScannerFactory(grammar).scanner(input);
		EarlyParser earlyParser = new EarlyParser(new SystemOutLogger(), grammar.build(), scanner);

		ParseTreeAnalyser<Program> programAnalyser = analyser(consumeExpression(statementListAnalysers, Program::of));
		return new SemanticAnalyser<>(earlyParser, programAnalyser);
	}

	private static List<Statement> addStatement(List<Statement> statements, Statement statement) {
		statements.add(statement);
		return statements;
	}
}
