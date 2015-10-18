package io.github.theangrydev.op.scanner;

import io.github.theangrydev.opper.grammar.GrammarBuilder;
import io.github.theangrydev.opper.scanner.BFAScanner;
import io.github.theangrydev.opper.scanner.Scanner;
import io.github.theangrydev.opper.scanner.definition.Expression;
import io.github.theangrydev.opper.scanner.definition.SymbolDefinition;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static io.github.theangrydev.opper.scanner.definition.AlternativeExpression.either;
import static io.github.theangrydev.opper.scanner.definition.CharacterClassExpression.characterClass;
import static io.github.theangrydev.opper.scanner.definition.CharacterExpression.character;
import static io.github.theangrydev.opper.scanner.definition.ConcatenateExpression.concatenate;
import static io.github.theangrydev.opper.scanner.definition.NotCharacters.notCharacaters;
import static io.github.theangrydev.opper.scanner.definition.RepeatExpression.repeat;
import static io.github.theangrydev.opper.scanner.definition.SymbolDefinition.definition;

public class ScannerFactory {

	private final GrammarBuilder grammarBuilder;

	public ScannerFactory(GrammarBuilder grammarBuilder) {
		this.grammarBuilder = grammarBuilder;
	}

	public Scanner scanner(Reader charactersToParse) {
		List<SymbolDefinition> symbolDefinitions = new ArrayList<>();

		symbolDefinitions.add(definition(grammarBuilder.symbolByName("&&"), concatenate(character('&'), character('&'))));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName("||"), concatenate(character('|'), character('|'))));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName("if"), concatenate(character('i'), character('f'))));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName("else"), concatenate(concatenate(concatenate(character('e'), character('l')), character('s')), character('e'))));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName("code"), concatenate(concatenate(concatenate(character('c'), character('o')), character('d')), character('e'))));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName("api"), concatenate(concatenate(character('a'), character('p')), character('i'))));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName("*"), character('*')));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName("+"), character('+')));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName("-"), character('-')));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName("/"), character('/')));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName(":"), character(':')));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName(";"), character(';')));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName(","), character(',')));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName("("), character('(')));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName(")"), character(')')));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName("=="), concatenate(character('='), character('='))));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName("<"), character('<')));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName(">"), character('>')));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName("<="), concatenate(character('<'), character('='))));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName(">="), concatenate(character('>'), character('='))));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName("!="), concatenate(character('!'), character('='))));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName(":"), character('!')));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName("="), character('=')));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName("."), character('.')));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName("Identifier"), concatenate(uppercase(), repeat(either(lowercase(), uppercase(), digit())))));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName("Integer"), integer()));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName("Real"), concatenate(concatenate(integer(), character('.')), integer())));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName("String"), concatenate(quote(), concatenate(repeat(either(concatenate(escape(), quote()), notQuote())), quote()))));
		symbolDefinitions.add(definition(grammarBuilder.symbolByName("Whitespace"), either(character(' '), character('\n'), character('\t'))));
		return new BFAScanner(symbolDefinitions, charactersToParse);
	}

	private Expression notQuote() {
		return characterClass(notCharacaters("\""));
	}

	private Expression escape() {
		return character('\\');
	}

	private Expression quote() {
		return character('\"');
	}

	public Expression integer() {
		return concatenate(digit(), repeat(digit()));
	}

	public Expression uppercase() {
		return either(character('A'), character('B'), character('C'), character('D'), character('E'), character('F'), character('G'), character('H'), character('I'), character('J'), character('K'), character('L'), character('M'), character('N'), character('O'), character('P'), character('Q'), character('R'), character('S'), character('T'), character('U'), character('V'), character('W'), character('X'), character('Y'), character('Z'));
	}

	public Expression lowercase() {
		return either(character('a'), character('b'), character('c'), character('d'), character('e'), character('f'), character('g'), character('h'), character('i'), character('j'), character('k'), character('l'), character('m'), character('n'), character('o'), character('p'), character('q'), character('r'), character('s'), character('t'), character('u'), character('v'), character('w'), character('x'), character('y'), character('z'));
	}

	public Expression digit() {
		return either(character('0'), character('1'), character('2'), character('3'), character('4'), character('5'), character('6'), character('7'), character('8'), character('9'));
	}
}
