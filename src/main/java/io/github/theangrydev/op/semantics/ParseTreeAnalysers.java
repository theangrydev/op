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
