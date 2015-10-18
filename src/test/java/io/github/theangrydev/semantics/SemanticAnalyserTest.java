package io.github.theangrydev.semantics;

import io.github.theangrydev.op.semantics.ParseTreeAnalysers;
import io.github.theangrydev.op.semantics.SemanticAnalyser;
import io.github.theangrydev.opper.grammar.Rule;
import io.github.theangrydev.opper.parser.Parser;
import io.github.theangrydev.opper.parser.tree.ParseTree;
import io.github.theangrydev.opper.parser.tree.ParseTreeNode;
import org.junit.Test;

import java.util.Optional;

import static io.github.theangrydev.op.semantics.BinaryExpressionAnalyser.binaryExpression;
import static io.github.theangrydev.op.semantics.ParseTreeLeafAnalyser.analyser;
import static io.github.theangrydev.op.semantics.ParseTreeNodeAnalyser.analyser;
import static io.github.theangrydev.opper.parser.tree.ParseTreeLeaf.leaf;
import static io.github.theangrydev.opper.parser.tree.ParseTreeNode.node;
import static io.github.theangrydev.semantics.SemanticAnalyserTest.FixedParser.parser;
import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.mockito.Mockito.mock;

public class SemanticAnalyserTest {

	@Test
	public void shouldEvaluateAParseTree() {
		Rule add = rule();
		Rule times = rule();
		Rule number = rule();

		ParseTreeAnalysers<Numeric> numericAnalysers = new ParseTreeAnalysers<>();
		numericAnalysers.add(times, analyser(binaryExpression(Multiplication::new, numericAnalysers)));
		numericAnalysers.add(add, analyser(binaryExpression(Addition::new, numericAnalysers)));
		numericAnalysers.add(number, analyser(Number::number));

		Parser parser = parser(parseTree(add, parseTree(add, parseTree(add, parseTree(number, "2"), parseTree("+"), parseTree(number, "3")), parseTree("+"), parseTree(number, "2")), parseTree("+"), parseTree(times, parseTree(number, "3"), parseTree("*"), parseTree(number, "4"))));

		SemanticAnalyser<Numeric> semanticAnalyser = new SemanticAnalyser<>(parser, numericAnalysers);

		assertThat(semanticAnalyser.analyse().get()).hasToString("Addition{left=Addition{left=Addition{left=Number{value=2}, right=Number{value=3}}, right=Number{value=2}}, right=Multiplication{left=Number{value=3}, right=Number{value=4}}}");
	}

	public static class FixedParser implements Parser {
		private final ParseTree parseTree;

		private FixedParser(ParseTree parseTree) {
			this.parseTree = parseTree;
		}

		public static FixedParser parser(ParseTree parseTree) {
			return new FixedParser(parseTree);
		}

		@Override
		public Optional<ParseTree> parse() {
			return Optional.of(parseTree);
		}
	}

	private ParseTree parseTree(String content) {
		return leaf(null, content);
	}

	private ParseTree parseTree(Rule rule, String content) {
		return leaf(rule, content);
	}

	private ParseTree parseTree(Rule rule, ParseTree... children) {
		ParseTreeNode parseTree = node(rule);
		for (ParseTree child : children) {
			parseTree.withChild(child);
		}
		return parseTree;
	}

	public static class Number implements Numeric {
		private final int value;

		private Number(int value) {
			this.value = value;
		}

		public static Number number(String content) {
			return new Number(Integer.parseInt(content));
		}

		@Override
		public String toString() {
			return "Number{" +
				"value=" + value +
				'}';
		}
	}

	public static class Addition implements Numeric {
		private final Numeric left;
		private final Numeric right;

		public Addition(Numeric left, Numeric right) {
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			return "Addition{" +
				"left=" + left +
				", right=" + right +
				'}';
		}
	}

	public static class Multiplication implements Numeric {
		private final Numeric left;
		private final Numeric right;

		public Multiplication(Numeric left, Numeric right) {
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			return "Multiplication{" +
				"left=" + left +
				", right=" + right +
				'}';
		}
	}

	public interface Numeric {

	}

	private Rule rule() {
		return mock(Rule.class);
	}
}
