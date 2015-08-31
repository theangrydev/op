package io.github.theangrydev.op.parser;

import java.util.List;

import static io.github.theangrydev.op.parser.CUPSymbols.terminalNames;
import static java.util.stream.Collectors.joining;

public class SyntaxError implements ProgramElement {
	private final Location goodSoFar;
	private final UnexpectedToken unexpectedToken;
	private final List<Integer> expectedTokenIds;

	private SyntaxError(UnexpectedToken unexpectedToken, Location goodSoFar, List<Integer> expectedTokenIds) {
		this.unexpectedToken = unexpectedToken;
		this.goodSoFar = goodSoFar;
		this.expectedTokenIds = expectedTokenIds;
	}

	public static SyntaxError of(UnexpectedToken unexpectedToken, Location goodSoFar, List<Integer> expectedTokenIds) {
		return new SyntaxError(unexpectedToken, goodSoFar, expectedTokenIds);
	}

	@Override
	public Location getLocation() {
		return Location.between(goodSoFar, unexpectedToken.getLocation());
	}

	@Override
	public String toString() {
		String expectedTokenNames = getExpectedTokenNames();
		if (!expectedTokenNames.isEmpty()) {
			return "Expected " + expectedTokenNames + " at " + getLocation() + " but found unexpected input " + unexpectedToken;
		} else {
			return unexpectedToken + " following last valid input at " + goodSoFar;
		}
	}

	public String getExpectedTokenNames() {
		return expectedTokenIds.stream()
			.filter(id -> id != CUPSymbols.error && id != CUPSymbols.EOF)
			.map(id -> terminalNames[id])
			.collect(joining(" or "));
	}
}
