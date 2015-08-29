package io.github.theangrydev;

import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;
import java_cup.runtime.Symbol;
import java_cup.runtime.SymbolFactory;

%%

/**
 *
 */
%line
%column

%apiprivate
%cupsym Symbol
%cup

%unicode

/**
 * Class name
 */
%class Scanner

/**
 * Constructor code:
 */
%ctorarg ComplexSymbolFactory symbolFactory
%init{
	this.symbolFactory = symbolFactory;
%init}

/**
 * Additional code:
 */
%{
private ComplexSymbolFactory symbolFactory;

private Symbol newSymbol(String name, int tokenId) {
	return symbolFactory.newSymbol(name, tokenId, new Location(yyline+1, yycolumn +1), new Location(yyline+1,yycolumn+yylength()));
}

private Symbol newSymbol(String name, int tokenId, Object value) {
	return symbolFactory.newSymbol(name, tokenId, new Location(yyline+1, yycolumn +1), new Location(yyline+1,yycolumn+yylength()), value);
}

private String innerText() {
	return new String(zzBuffer, zzStartRead + 1, zzMarkedPos-zzStartRead - 2);
}
%}

/**
 * The value used for EOF:
 */
%eofval{
	return newSymbol("EOF", Symbols.EOF);
%eofval}

/**
 * Pattern definitions:
 */
Escape          = \\
Quote           = \"
NotQuote        = [^\"]
String          = {Quote}({Escape}{Quote}|{NotQuote})*{Quote}
Digit           = [0-9]
Identifier      = [:uppercase:][:jletterdigit:]*
Integer         = [:digit:]+
Real            = {Integer}\.{Integer}
LeftBrace       = \{
RightBrace      = \}
Whitespace      = [ \n\t]


%%
/**
 * Lexical rules:
 */
"&&"            { return newSymbol("&&", Symbols.AND); }
"||"            { return newSymbol("||", Symbols.OR); }
"if"            { return newSymbol("if", Symbols.IF); }
"else"          { return newSymbol("else", Symbols.ELSE); }
"code"          { return newSymbol("code", Symbols.CODE); }
"api"           { return newSymbol("api", Symbols.API); }
"*"             { return newSymbol("*", Symbols.TIMES); }
"+"             { return newSymbol("+", Symbols.PLUS); }
"-"             { return newSymbol("-", Symbols.MINUS); }
"/"             { return newSymbol("/", Symbols.DIVIDE); }
";"             { return newSymbol(";", Symbols.SEMICOLON); }
","             { return newSymbol(",", Symbols.COMMA); }
"("             { return newSymbol("(", Symbols.LEFT_PARENTHESIS); }
")"             { return newSymbol(")", Symbols.RIGHT_PARENTHESIS); }
"=="            { return newSymbol("==", Symbols.EQUAL_TO); }
"<"             { return newSymbol("<", Symbols.LESS_THAN); }
">"             { return newSymbol(">", Symbols.GREATER_THAN); }
"<="            { return newSymbol("<=", Symbols.LESS_THAN_OR_EQUAL_TO); }
">="            { return newSymbol(">=", Symbols.GREATER_THAN_OR_EQUAL_TO); }
"!="            { return newSymbol("!=", Symbols.NOT_EQUAL_TO); }
":"             { return newSymbol(":", Symbols.COLON); }
"="             { return newSymbol("=", Symbols.ASSIGNMENT); }
"."             { return newSymbol(".", Symbols.DOT); }
{Identifier}    { return newSymbol("identifier", Symbols.IDENTIFIER, yytext()); }
{Integer}       { return newSymbol("integer", Symbols.INTEGER, new Integer(yytext())); }
{Real}          { return newSymbol("real", Symbols.REAL, new Double(yytext())); }
{String}        { return newSymbol("string", Symbols.STRING, innerText()); }
{Whitespace}    { /* Ignore whitespace. */ }
.               { System.out.println("Illegal char, '" + yytext() + "' line: " + yyline + ", column: " + yychar); }
