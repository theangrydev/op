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
