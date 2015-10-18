package io.github.theangrydev.op.parser;

import io.github.theangrydev.opper.scanner.Location;

import java.util.List;

public class Program implements ProgramElement {

	private final List<Statement> statements;

	private Program(List<Statement> statements) {
		this.statements = statements;
	}

	public static Program of(List<Statement> statements) {
		return new Program(statements);
	}

	public List<Statement> statements() {
		return statements;
	}

	@Override
	public String toString() {
		return "{" + statements.toString() + "}";
	}

	@Override
	public Location getLocation() {
		return null;
//		return Location.between(getFirstStatement(), getLastStatement());
	}

	public Statement getLastStatement() {
		return statements.get(statements.size() - 1);
	}

	public Statement getFirstStatement() {
		return statements.get(0);
	}
}
