/*
 * Copyright 2015-2016 Liam Williams <liam.williams@zoho.com>.
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
package io.github.theangrydev.op.language;

import io.github.theangrydev.op.generation.ProgramCompiler;
import io.github.theangrydev.opper.scanner.Location;

import java.util.List;

public class Program implements ProgramElement<Program> {

	private final Location location;
	private final List<Statement<?>> statements;

	private Program(List<Statement<?>> statements) {
		this.location = location(statements);
		this.statements = statements;
	}

	private Location location(List<Statement<?>> statements) {
		if (statements.isEmpty()) {
			return Location.location(0, 0, 0, 0);
		}
		return locationBetween(statements.get(0), statements.get(statements.size() - 1));
	}

	public static Program of(List<Statement<?>> statements) {
		return new Program(statements);
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

	@Override
	public void checkTypes(ProgramCompiler programCompiler) {
		programCompiler.populateDefaultTypes();
		for (Statement statement : statements) {
			statement.checkTypes(programCompiler);
		}
	}

	@Override
	public void compile(ProgramCompiler programCompiler) {
		for (Statement statement : statements) {
			statement.compile(programCompiler);
		}
	}
}
