package io.github.theangrydev.op.language;

import io.github.theangrydev.opper.scanner.Location;

import java.util.List;

public class Program implements ProgramElement<Program> {

	private final Location location;
	private final List<Statement<?>> statements;

	private Program(Location location, List<Statement<?>> statements) {
		this.location = location;
		this.statements = statements;
	}

	public static Program of(List<Statement<?>> statements) {
		return new Program(ProgramElement.locationBetween(statements.get(0), statements.get(statements.size() - 1)), statements);
	}

	public List<Statement<?>> statements() {
		return statements;
	}

	@Override
	public String toString() {
		return "{" + statements.toString() + "}";
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public Program simplify() {
		for (int i = 0; i < statements.size(); i++) {
			statements.set(i, statements.get(i).simplify());
		}
		return this;
	}
}
