package io.github.theangrydev.op.parser;

import io.github.theangrydev.opper.common.DoNothingLogger;
import io.github.theangrydev.opper.grammar.Grammar;
import io.github.theangrydev.opper.parser.EarlyParserFactory;
import io.github.theangrydev.opper.parser.ParserFactory;

public class ProgramParserFactory {

	public static ParserFactory programParserFactory(Grammar grammar) {
		return new EarlyParserFactory(new DoNothingLogger(), grammar);
	}
}
