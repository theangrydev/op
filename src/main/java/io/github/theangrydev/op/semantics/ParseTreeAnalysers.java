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
package io.github.theangrydev.op.semantics;

import com.google.common.base.Preconditions;
import io.github.theangrydev.opper.grammar.Rule;
import io.github.theangrydev.opper.parser.tree.ParseTree;

import java.util.HashMap;
import java.util.Map;

public class ParseTreeAnalysers<Result> implements ParseTreeAnalyser<Result> {

	private Map<Rule, ParseTreeAnalyser<? extends Result>> parseTreeAnalysers;

	public ParseTreeAnalysers() {
		this.parseTreeAnalysers = new HashMap<>();
	}

	public void add(Rule rule, ParseTreeAnalyser<? extends Result> parseTreeAnalyser) {
		Preconditions.checkNotNull(rule);
		parseTreeAnalysers.put(rule, parseTreeAnalyser);
	}

	@Override
	public Result analyse(ParseTree parseTree) {
		return parseTreeAnalysers.get(parseTree.rule()).analyse(parseTree);
	}
}
