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
