package io.github.theangrydev.op.parser;

import com.google.common.collect.Lists;
import io.github.theangrydev.op.language.*;
import io.github.theangrydev.op.semantics.ParseTreeAnalyser;
import io.github.theangrydev.op.semantics.ParseTreeAnalysers;
import io.github.theangrydev.opper.grammar.Grammar;

import java.util.List;

import static io.github.theangrydev.op.semantics.BinaryExpressionAnalyser.binaryExpression;
import static io.github.theangrydev.op.semantics.ConsumeExpressionAnalyser.consumeExpression;
import static io.github.theangrydev.op.semantics.ParseTreeLeafAnalyser.analyser;
import static io.github.theangrydev.op.semantics.ParseTreeNodeAnalyser.analyser;

public class ProgramParseTreeAnalyserFactory {
	public static ParseTreeAnalyser<Program> programParseTreeAnalyser(Grammar grammar) {
		ParseTreeAnalyser<TypeExpression> typeExpression = analyser(consumeExpression(analyser(TypeExpression::of)));

		ParseTreeAnalysers<Expression> expressions = new ParseTreeAnalysers<>();
		expressions.add(grammar.ruleByDefinition("Expression", "Integer"), analyser(consumeExpression(analyser(IntegerConstant::of))));
		expressions.add(grammar.ruleByDefinition("Expression", "Real"), analyser(consumeExpression(analyser(RealConstant::of))));
		expressions.add(grammar.ruleByDefinition("Expression", "String"), analyser(consumeExpression(analyser(StringConstant::of))));
		expressions.add(grammar.ruleByDefinition("Expression", "TypeExpression"), analyser(consumeExpression(typeExpression)));
		expressions.add(grammar.ruleByDefinition("Expression", "Expression", "+", "Expression"), analyser(binaryExpression(Addition::add, expressions)));

		ParseTreeAnalyser<TypeDeclaration> typeDeclaration = analyser(binaryExpression(TypeDeclaration::of, typeExpression));

		ParseTreeAnalysers<Statement> statements = new ParseTreeAnalysers<>();
		statements.add(grammar.ruleByDefinition("Statement", "TypeDeclaration", "=", "Expression"), analyser(binaryExpression(TypeDeclarationAssignment::of, typeDeclaration, expressions)));
		statements.add(grammar.ruleByDefinition("Statement", "TypeExpression", "=", "Expression"), analyser(binaryExpression(ExistingTypeAssignment::of, typeExpression, expressions)));

		ParseTreeAnalyser<Statement> statementWithSemicolon = analyser(consumeExpression(statements));

		ParseTreeAnalysers<List<Statement>> statementListAnalysers = new ParseTreeAnalysers<>();
		statementListAnalysers.add(grammar.ruleByDefinition("StatementList", "StatementWithSemicolon"), analyser(consumeExpression(statementWithSemicolon, Lists::newArrayList)));
		statementListAnalysers.add(grammar.ruleByDefinition("StatementList", "StatementList", "StatementWithSemicolon"), analyser(binaryExpression(ProgramParseTreeAnalyserFactory::addStatement, 0, statementListAnalysers, 1, statementWithSemicolon)));

		return analyser(consumeExpression(statementListAnalysers, Program::of));
	}

	private static List<Statement> addStatement(List<Statement> statements, Statement statement) {
		statements.add(statement);
		return statements;
	}
}
