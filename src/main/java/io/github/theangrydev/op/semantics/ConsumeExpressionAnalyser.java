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


import io.github.theangrydev.opper.parser.tree.ParseTree;

import java.util.List;

public class ConsumeExpressionAnalyser<Result, Argument> implements ParseTreeNodeAnalyser.NodeAnalyser<Result> {

	private final ParseTreeAnalyser<Argument> argumentAnalyser;
	private final UnaryConstructor<Result, Argument> constructor;

	private ConsumeExpressionAnalyser(ParseTreeAnalyser<Argument> argumentAnalyser, UnaryConstructor<Result, Argument> constructor) {
		this.argumentAnalyser = argumentAnalyser;
		this.constructor = constructor;
	}

	public static <Result, Argument> ConsumeExpressionAnalyser<Result, Argument> consumeExpression(ParseTreeAnalyser<Argument> argumentAnalyser, UnaryConstructor<Result, Argument> constructor) {
		return new ConsumeExpressionAnalyser<>(argumentAnalyser, constructor);
	}

	public static <Argument> ConsumeExpressionAnalyser<Argument, Argument> consumeExpression(ParseTreeAnalyser<Argument> argumentAnalyser) {
		return new ConsumeExpressionAnalyser<>(argumentAnalyser, argument -> argument);
	}

	@Override
	public Result analyse(List<ParseTree> children) {
		return constructor.construct(argumentAnalyser.analyse(children.get(0)));
	}

	@FunctionalInterface
	public interface UnaryConstructor<Result, Argument> {
		Result construct(Argument argument);
	}
}
