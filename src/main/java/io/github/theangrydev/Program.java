package io.github.theangrydev;

import java.util.List;

public class Program {

	private final List<Statement> statements;

	private Program(List<Statement> statements) {
		this.statements = statements;
		System.out.println(this);
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
}
