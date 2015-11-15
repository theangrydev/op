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

import io.github.theangrydev.opper.common.DoNothingLogger;
import io.github.theangrydev.opper.grammar.Grammar;
import io.github.theangrydev.opper.parser.EarlyParserFactory;
import io.github.theangrydev.opper.parser.ParserFactory;

import static io.github.theangrydev.op.parser.ProgramGrammar.PROGRAM_GRAMMAR;

public class ProgramParserFactory {

	public static ParserFactory PROGRAM_PARSER_FACTORY = programParserFactory(PROGRAM_GRAMMAR);

	private static ParserFactory programParserFactory(Grammar grammar) {
		return new EarlyParserFactory(new DoNothingLogger(), grammar);
	}
}
