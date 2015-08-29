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

private Symbol symbol(String name, int tokenId) {
	return symbolFactory.newSymbol(name, tokenId, new Location(yyline+1, yycolumn +1), new Location(yyline+1,yycolumn+yylength()));
}

private Symbol symbol(String name, int tokenId, Object value) {
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
	return symbol("EOF", Symbols.EOF);
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
"&&"            { return symbol("&&", Symbols.AND); }
"||"            { return symbol("||", Symbols.OR); }
"if"            { return symbol("if", Symbols.IF); }
"else"          { return symbol("else", Symbols.ELSE); }
"code"          { return symbol("code", Symbols.CODE); }
"api"           { return symbol("api", Symbols.API); }
"*"             { return symbol("*", Symbols.TIMES); }
"+"             { return symbol("+", Symbols.PLUS); }
"-"             { return symbol("-", Symbols.MINUS); }
"/"             { return symbol("/", Symbols.DIVIDE); }
";"             { return symbol(";", Symbols.SEMICOLON); }
","             { return symbol(",", Symbols.COMMA); }
"("             { return symbol("(", Symbols.LEFT_PARENTHESIS); }
")"             { return symbol(")", Symbols.RIGHT_PARENTHESIS); }
"=="            { return symbol("==", Symbols.EQUAL_TO); }
"<"             { return symbol("<", Symbols.LESS_THAN); }
">"             { return symbol(">", Symbols.GREATER_THAN); }
"<="            { return symbol("<=", Symbols.LESS_THAN_OR_EQUAL_TO); }
">="            { return symbol(">=", Symbols.GREATER_THAN_OR_EQUAL_TO); }
"!="            { return symbol("!=", Symbols.NOT_EQUAL_TO); }
":"             { return symbol(":", Symbols.COLON); }
"="             { return symbol("=", Symbols.ASSIGNMENT); }
"."             { return symbol(".", Symbols.DOT); }
{Identifier}    { return symbol("identifier", Symbols.IDENTIFIER, yytext()); }
{Integer}       { return symbol("integer", Symbols.INTEGER, new Integer(yytext())); }
{Real}          { return symbol("real", Symbols.REAL, new Double(yytext())); }
{String}        { return symbol("string", Symbols.STRING, innerText()); }
{Whitespace}    { /* Ignore whitespace. */ }
.               { System.out.println("Illegal char, '" + yytext() + "' line: " + yyline + ", column: " + yychar); }
