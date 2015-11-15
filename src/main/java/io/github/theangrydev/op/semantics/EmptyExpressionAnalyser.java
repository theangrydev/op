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

import io.github.theangrydev.opper.parser.tree.ParseTree;

public class EmptyExpressionAnalyser<Result> implements ParseTreeAnalyser<Result> {

	private final NoArgumentConstructor<Result> constructor;

	private EmptyExpressionAnalyser(NoArgumentConstructor<Result> constructor) {
		this.constructor = constructor;
	}

	public static <Result> EmptyExpressionAnalyser<Result> emptyExpression(NoArgumentConstructor<Result> constructor) {
		return new EmptyExpressionAnalyser<>(constructor);
	}

	@Override
	public Result analyse(ParseTree parseTree) {
		return constructor.construct();
	}

	@FunctionalInterface
	public interface NoArgumentConstructor<Result> {
		Result construct();
	}
}
