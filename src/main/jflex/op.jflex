package io.github.theangrydev.op.scanner;

import io.github.theangrydev.op.parser.CUPSymbols;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;
import java_cup.runtime.Symbol;

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
%class JFlexScanner

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
	return symbol("EOF", CUPSymbols.EOF);
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
"&&"            { return symbol("&&", CUPSymbols.AND); }
"||"            { return symbol("||", CUPSymbols.OR); }
"if"            { return symbol("if", CUPSymbols.IF); }
"else"          { return symbol("else", CUPSymbols.ELSE); }
"code"          { return symbol("code", CUPSymbols.CODE); }
"api"           { return symbol("api", CUPSymbols.API); }
"*"             { return symbol("*", CUPSymbols.TIMES); }
"+"             { return symbol("+", CUPSymbols.PLUS); }
"-"             { return symbol("-", CUPSymbols.MINUS); }
"/"             { return symbol("/", CUPSymbols.DIVIDE); }
";"             { return symbol(";", CUPSymbols.SEMICOLON); }
","             { return symbol(",", CUPSymbols.COMMA); }
"("             { return symbol("(", CUPSymbols.LEFT_PARENTHESIS); }
")"             { return symbol(")", CUPSymbols.RIGHT_PARENTHESIS); }
"=="            { return symbol("==", CUPSymbols.EQUAL_TO); }
"<"             { return symbol("<", CUPSymbols.LESS_THAN); }
">"             { return symbol(">", CUPSymbols.GREATER_THAN); }
"<="            { return symbol("<=", CUPSymbols.LESS_THAN_OR_EQUAL_TO); }
">="            { return symbol(">=", CUPSymbols.GREATER_THAN_OR_EQUAL_TO); }
"!="            { return symbol("!=", CUPSymbols.NOT_EQUAL_TO); }
":"             { return symbol(":", CUPSymbols.COLON); }
"="             { return symbol("=", CUPSymbols.ASSIGNMENT); }
"."             { return symbol(".", CUPSymbols.DOT); }
{Identifier}    { return symbol("identifier", CUPSymbols.IDENTIFIER, yytext()); }
{Integer}       { return symbol("integer", CUPSymbols.INTEGER, new Integer(yytext())); }
{Real}          { return symbol("real", CUPSymbols.REAL, new Double(yytext())); }
{String}        { return symbol("string", CUPSymbols.STRING, innerText()); }
{Whitespace}    { /* Ignore whitespace. */ }
.               { System.out.println("Illegal char, '" + yytext() + "' line: " + yyline + ", column: " + yychar); }
