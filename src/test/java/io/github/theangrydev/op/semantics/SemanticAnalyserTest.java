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

import io.github.theangrydev.opper.grammar.Rule;
import io.github.theangrydev.opper.parser.Parser;
import io.github.theangrydev.opper.parser.tree.ParseTree;
import io.github.theangrydev.opper.parser.tree.ParseTreeNode;
import io.github.theangrydev.opper.scanner.Location;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;
import java.util.Optional;

import static io.github.theangrydev.op.semantics.BinaryExpressionAnalyser.binaryExpression;
import static io.github.theangrydev.op.semantics.ParseTreeLeafAnalyser.analyser;
import static io.github.theangrydev.op.semantics.ParseTreeNodeAnalyser.analyser;
import static io.github.theangrydev.opper.parser.tree.ParseTreeLeaf.leaf;
import static io.github.theangrydev.opper.parser.tree.ParseTreeNode.node;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class SemanticAnalyserTest {

	@Test
	public void shouldEvaluateAParseTree() throws IOException {
		Rule add = rule();
		Rule times = rule();
		Rule number = rule();

		ParseTreeAnalysers<Numeric> numericAnalysers = new ParseTreeAnalysers<>();
		numericAnalysers.add(times, analyser(binaryExpression(Multiplication::new, numericAnalysers)));
		numericAnalysers.add(add, analyser(binaryExpression(Addition::new, numericAnalysers)));
		numericAnalysers.add(number, analyser(Number::number));

		Parser parser = FixedParser.parser(parseTree(add, parseTree(add, parseTree(add, parseTree(number, "2"), parseTree("+"), parseTree(number, "3")), parseTree("+"), parseTree(number, "2")), parseTree("+"), parseTree(times, parseTree(number, "3"), parseTree("*"), parseTree(number, "4"))));

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
		return leaf(null, content, null);
	}

	private ParseTree parseTree(Rule rule, String content) {
		return leaf(rule, content, null);
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

		public static Number number(Location location, String content) {
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

