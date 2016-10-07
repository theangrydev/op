/*
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

import com.google.common.collect.Lists;
import io.github.theangrydev.op.language.Program;
import io.github.theangrydev.op.language.Statement;
import io.github.theangrydev.op.language.assignment.ExistingTypeAssignment;
import io.github.theangrydev.op.language.assignment.Type;
import io.github.theangrydev.op.language.assignment.TypeDeclaration;
import io.github.theangrydev.op.language.assignment.TypeDeclarationAssignment;
import io.github.theangrydev.op.language.expression.*;
import io.github.theangrydev.op.semantics.ParseTreeAnalyser;
import io.github.theangrydev.op.semantics.ParseTreeAnalysers;
import io.github.theangrydev.op.semantics.ParseTreeLeafAnalyser;
import io.github.theangrydev.opper.grammar.Grammar;

import java.util.List;

import static io.github.theangrydev.op.parser.ProgramGrammar.PROGRAM_GRAMMAR;
import static io.github.theangrydev.op.semantics.BinaryExpressionAnalyser.binaryExpression;
import static io.github.theangrydev.op.semantics.ConsumeExpressionAnalyser.consumeExpression;
import static io.github.theangrydev.op.semantics.EmptyExpressionAnalyser.emptyExpression;
import static io.github.theangrydev.op.semantics.ParseTreeLeafAnalyser.analyser;
import static io.github.theangrydev.op.semantics.ParseTreeNodeAnalyser.analyser;

public class ProgramParseTreeAnalyserFactory {

	public static ParseTreeAnalyser<Program> PROGRAM_PARSE_TREE_ANALYSER_FACTORY = programParseTreeAnalyser(PROGRAM_GRAMMAR);

	private static ParseTreeAnalyser<Program> programParseTreeAnalyser(Grammar grammar) {
		ParseTreeAnalyser<TypeExpression> typeExpression = analyser(consumeExpression(analyser(TypeExpression::typeExpression)));

		ParseTreeAnalysers<Expression> expressions = new ParseTreeAnalysers<>();
		expressions.add(grammar.ruleByDefinition("Expression", "Integer"), analyser(consumeExpression(analyser(IntegerConstant::integerConstant))));
		expressions.add(grammar.ruleByDefinition("Expression", "Real"), analyser(consumeExpression(analyser(RealConstant::realConstant))));
		expressions.add(grammar.ruleByDefinition("Expression", "String"), analyser(consumeExpression(analyser(StringConstant::quotedStringConstant))));
		expressions.add(grammar.ruleByDefinition("Expression", "TypeExpression"), analyser(consumeExpression(typeExpression)));
		expressions.add(grammar.ruleByDefinition("Expression", "Expression", "+", "Expression"), analyser(binaryExpression(Addition::add, expressions)));

		ParseTreeAnalyser<Type> type = analyser(consumeExpression(ParseTreeLeafAnalyser.analyser(Type::type)));
		ParseTreeAnalyser<TypeDeclaration> typeDeclaration = analyser(binaryExpression(TypeDeclaration::of, typeExpression, type));

		ParseTreeAnalysers<Statement> statements = new ParseTreeAnalysers<>();
		statements.add(grammar.ruleByDefinition("Statement", "TypeDeclaration", "=", "Expression"), analyser(binaryExpression(TypeDeclarationAssignment::of, typeDeclaration, expressions)));
		statements.add(grammar.ruleByDefinition("Statement", "TypeExpression", "=", "Expression"), analyser(binaryExpression(ExistingTypeAssignment::of, typeExpression, expressions)));

		ParseTreeAnalyser<Statement> statementWithSemicolon = analyser(consumeExpression(statements));

		ParseTreeAnalysers<List<Statement<?>>> statementListAnalysers = new ParseTreeAnalysers<>();
		statementListAnalysers.add(grammar.ruleByDefinition("StatementList", "Empty"), emptyExpression(Lists::newArrayList));
		statementListAnalysers.add(grammar.ruleByDefinition("StatementList", "StatementList", "StatementWithSemicolon"), analyser(binaryExpression(ProgramParseTreeAnalyserFactory::addStatement, 0, statementListAnalysers, 1, statementWithSemicolon)));

		return analyser(consumeExpression(statementListAnalysers, Program::of));
	}

	private static List<Statement<?>> addStatement(List<Statement<?>> statements, Statement statement) {
		statements.add(statement);
		return statements;
	}
}
