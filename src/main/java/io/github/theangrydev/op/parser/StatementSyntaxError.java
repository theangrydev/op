package io.github.theangrydev.op.parser;

public class StatementSyntaxError implements Statement {
	private final SyntaxError syntaxError;

	private StatementSyntaxError(SyntaxError syntaxError) {
		this.syntaxError = syntaxError;
	}

	public static StatementSyntaxError of(SyntaxError syntaxError) {
		return new StatementSyntaxError(syntaxError);
	}

	@Override
	public Location getLocation() {
		return syntaxError.getLocation();
	}

	@Override
	public String toString() {
		return "Syntax error in statement: " + syntaxError;
	}
}
