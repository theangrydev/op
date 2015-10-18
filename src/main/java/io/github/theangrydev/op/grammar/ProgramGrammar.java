package io.github.theangrydev.op.grammar;

import io.github.theangrydev.opper.grammar.Grammar;
import io.github.theangrydev.opper.grammar.GrammarBuilder;

public class ProgramGrammar {

	public static Grammar programGrammar() {
		return new GrammarBuilder()
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
			.withRule("Expression", "String")
			.withRule("Expression", "TypeExpression")
			.withRule("Expression", "Expression", "+", "Expression")
			.build();
	}
}
