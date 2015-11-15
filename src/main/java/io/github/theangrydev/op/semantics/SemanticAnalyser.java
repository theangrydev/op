/**
 * Copyright 2015 Liam Williams <liam.williams@zoho.com>.
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
package io.github.theangrydev.op.semantics;


import io.github.theangrydev.opper.parser.Parser;

import java.util.Optional;

public class SemanticAnalyser<Result> {

	private final Parser parser;
	private final ParseTreeAnalyser<Result> parseTreeAnalyser;

	public SemanticAnalyser(Parser parser, ParseTreeAnalyser<Result> parseTreeAnalyser) {
		this.parser = parser;
		this.parseTreeAnalyser = parseTreeAnalyser;
	}

	public Optional<Result> analyse() {
		return parser.parse().map(parseTreeAnalyser::analyse);
	}
}
