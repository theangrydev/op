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

import io.github.theangrydev.opper.grammar.Grammar;
import io.github.theangrydev.opper.grammar.GrammarBuilder;

public class ProgramGrammar {

	public static Grammar PROGRAM_GRAMMAR = new GrammarBuilder()
		.withAcceptanceSymbol("Accept")
		.withStartSymbol("Program")
		.withEmptySymbol("Empty")
		.withRule("Program", "StatementList")
		.withRule("StatementList", "StatementList", "StatementWithSemicolon")
		.withRule("StatementList", "Empty")
		.withRule("StatementWithSemicolon", "Statement", ";")
		.withRule("Statement", "TypeDeclaration", "=", "Expression")
		.withRule("Statement", "TypeExpression", "=", "Expression")
		.withRule("TypeDeclaration", "TypeExpression", ":", "TypeExpression")
		.withRule("TypeExpression", "Identifier")
		.withRule("Expression", "Integer")
		.withRule("Expression", "Real")
		.withRule("Expression", "String")
		.withRule("Expression", "TypeExpression")
		.withRule("Expression", "Expression", "+", "Expression")
		.build();
}
