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

public class BinaryExpressionAnalyser<Result, Left, Right> implements ParseTreeNodeAnalyser.NodeAnalyser<Result> {

	private final int leftIndex;
	private final int rightIndex;
	private final BinaryConstructor<Result, Left, Right> binaryConstructor;
	private final ParseTreeAnalyser<Left> leftAnalyser;
	private final ParseTreeAnalyser<Right> rightAnalyser;

	private BinaryExpressionAnalyser(BinaryConstructor<Result, Left, Right> binaryConstructor, int leftIndex, ParseTreeAnalyser<Left> leftAnalyser, int rightIndex, ParseTreeAnalyser<Right> rightAnalyser) {
		this.binaryConstructor = binaryConstructor;
		this.leftAnalyser = leftAnalyser;
		this.leftIndex = leftIndex;
		this.rightAnalyser = rightAnalyser;
		this.rightIndex = rightIndex;
	}

	public static <Result, Left, Right> BinaryExpressionAnalyser<Result, Left, Right> binaryExpression(BinaryConstructor<Result, Left, Right> binaryConstructor, ParseTreeAnalyser<Left> leftAnalyser, ParseTreeAnalyser<Right> rightAnalyser) {
		return binaryExpression(binaryConstructor, 0, leftAnalyser, 2, rightAnalyser);
	}

	public static <Result, Argument> BinaryExpressionAnalyser<Result, Argument, Argument> binaryExpression(BinaryConstructor<Result, Argument, Argument> binaryConstructor, ParseTreeAnalyser<Argument> argumentAnalyser) {
		return binaryExpression(binaryConstructor, argumentAnalyser, argumentAnalyser);
	}

	public static <Result, Left, Right> BinaryExpressionAnalyser<Result, Left, Right>  binaryExpression(BinaryConstructor<Result, Left, Right> binaryConstructor, int leftIndex, ParseTreeAnalyser<Left> leftAnalyser, int rightIndex, ParseTreeAnalyser<Right> rightAnalyser) {
		return new BinaryExpressionAnalyser<>(binaryConstructor, leftIndex, leftAnalyser, rightIndex, rightAnalyser);
	}

	@Override
	public final Result analyse(List<ParseTree> children) {
		return binaryConstructor.construct(leftAnalyser.analyse(children.get(leftIndex)), rightAnalyser.analyse(children.get(rightIndex)));
	}

	@FunctionalInterface
	public interface BinaryConstructor<Result, Left, Right> {
		Result construct(Left left, Right right);
	}
}
