/*
 * Copyright 2015-2020 Liam Williams <liam.williams@zoho.com>.
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


import io.github.theangrydev.opper.parser.tree.ParseTree;

import java.util.List;

public class ParseTreeNodeAnalyser<Result> implements ParseTreeAnalyser<Result> {

	private final NodeAnalyser<Result> nodeAnalyser;

	private ParseTreeNodeAnalyser(NodeAnalyser<Result> nodeAnalyser) {
		this.nodeAnalyser = nodeAnalyser;
	}

	public static <Result> ParseTreeAnalyser<Result> analyser(NodeAnalyser<Result> nodeAnalyser) {
		return new ParseTreeNodeAnalyser<>(nodeAnalyser);
	}

	@Override
	public final Result analyse(ParseTree parseTree) {
		return nodeAnalyser.analyse(parseTree.children());
	}

	@FunctionalInterface
	public interface NodeAnalyser<Result> {
		Result analyse(List<ParseTree> children);
	}
}
