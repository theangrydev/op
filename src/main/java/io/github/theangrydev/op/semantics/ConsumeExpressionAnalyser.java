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
