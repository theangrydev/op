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
import io.github.theangrydev.opper.scanner.BFAScannerFactory;
import io.github.theangrydev.opper.scanner.ScannerFactory;
import io.github.theangrydev.opper.scanner.definition.Expression;
import io.github.theangrydev.opper.scanner.definition.SymbolDefinition;

import java.util.ArrayList;
import java.util.List;

import static io.github.theangrydev.op.parser.ProgramGrammar.PROGRAM_GRAMMAR;
import static io.github.theangrydev.opper.scanner.definition.AlternativeExpression.either;
import static io.github.theangrydev.opper.scanner.definition.CharacterClassExpression.characterClass;
import static io.github.theangrydev.opper.scanner.definition.CharacterExpression.character;
import static io.github.theangrydev.opper.scanner.definition.ConcatenateExpression.concatenate;
import static io.github.theangrydev.opper.scanner.definition.NotCharacters.notCharacaters;
import static io.github.theangrydev.opper.scanner.definition.RepeatExpression.repeat;
import static io.github.theangrydev.opper.scanner.definition.SymbolDefinition.definition;

public class ProgramScannerFactory {

	public static final ScannerFactory PROGRAM_SCANNER_FACTORY = programScannerFactory(PROGRAM_GRAMMAR);

	private static ScannerFactory programScannerFactory(Grammar grammar) {
		List<SymbolDefinition> symbolDefinitions = new ArrayList<>();
		symbolDefinitions.add(definition(grammar.symbolByName("+"), character('+')));
		symbolDefinitions.add(definition(grammar.symbolByName(";"), character(';')));
		symbolDefinitions.add(definition(grammar.symbolByName(":"), character(':')));
		symbolDefinitions.add(definition(grammar.symbolByName("="), character('=')));
		symbolDefinitions.add(definition(grammar.symbolByName("Identifier"), concatenate(uppercase(), repeat(either(lowercase(), uppercase(), digit())))));
		symbolDefinitions.add(definition(grammar.symbolByName("Integer"), integer()));
		symbolDefinitions.add(definition(grammar.symbolByName("Real"), concatenate(concatenate(integer(), character('.')), integer())));
		symbolDefinitions.add(definition(grammar.symbolByName("String"), concatenate(quote(), concatenate(repeat(either(concatenate(escape(), quote()), notQuote())), quote()))));
		return new BFAScannerFactory(symbolDefinitions);
	}

	private static  Expression notQuote() {
		return characterClass(notCharacaters("\""));
	}

	private static  Expression escape() {
		return character('\\');
	}

	private static  Expression quote() {
		return character('\"');
	}

	private static  Expression integer() {
		return concatenate(digit(), repeat(digit()));
	}

	private static  Expression uppercase() {
		return either(character('A'), character('B'), character('C'), character('D'), character('E'), character('F'), character('G'), character('H'), character('I'), character('J'), character('K'), character('L'), character('M'), character('N'), character('O'), character('P'), character('Q'), character('R'), character('S'), character('T'), character('U'), character('V'), character('W'), character('X'), character('Y'), character('Z'));
	}

	private static  Expression lowercase() {
		return either(character('a'), character('b'), character('c'), character('d'), character('e'), character('f'), character('g'), character('h'), character('i'), character('j'), character('k'), character('l'), character('m'), character('n'), character('o'), character('p'), character('q'), character('r'), character('s'), character('t'), character('u'), character('v'), character('w'), character('x'), character('y'), character('z'));
	}

	private static Expression digit() {
		return either(character('0'), character('1'), character('2'), character('3'), character('4'), character('5'), character('6'), character('7'), character('8'), character('9'));
	}
}
