package io.github.theangrydev.op.parser;

import io.github.theangrydev.opper.grammar.Grammar;
import io.github.theangrydev.opper.scanner.BFAScannerFactory;
import io.github.theangrydev.opper.scanner.ScannerFactory;
import io.github.theangrydev.opper.scanner.definition.Expression;
import io.github.theangrydev.opper.scanner.definition.SymbolDefinition;

import java.util.ArrayList;
import java.util.List;

import static io.github.theangrydev.opper.scanner.definition.AlternativeExpression.either;
import static io.github.theangrydev.opper.scanner.definition.CharacterClassExpression.characterClass;
import static io.github.theangrydev.opper.scanner.definition.CharacterExpression.character;
import static io.github.theangrydev.opper.scanner.definition.ConcatenateExpression.concatenate;
import static io.github.theangrydev.opper.scanner.definition.NotCharacters.notCharacaters;
import static io.github.theangrydev.opper.scanner.definition.RepeatExpression.repeat;
import static io.github.theangrydev.opper.scanner.definition.SymbolDefinition.definition;

public class ProgramScannerFactory {

	public static ScannerFactory programScannerFactory(Grammar grammar) {
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
