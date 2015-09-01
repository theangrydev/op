package io.github.theangrydev.op.parser;

import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;

import java.util.List;
import java.util.Optional;

import static io.github.theangrydev.op.parser.CUPSymbols.terminalNames;
import static java.util.stream.Collectors.joining;

public class SyntaxError implements ProgramElement {
	private final Optional<ProgramElement> lastValidElement;
	private final List<Integer> expectedTokenIds;
	private final Location unexpectedTokenLocation;
	private final int unexpectedTokenId;

	private SyntaxError(Optional<ProgramElement> lastValidElement, Location unexpectedTokenLocation, int unexpectedTokenId, List<Integer> expectedTokenIds) {
		this.lastValidElement = lastValidElement;
		this.expectedTokenIds = expectedTokenIds;
		this.unexpectedTokenLocation = unexpectedTokenLocation;
		this.unexpectedTokenId = unexpectedTokenId;
	}

	public static SyntaxError of(ComplexSymbol previousToken, ComplexSymbol unexpectedToken, List<Integer> expectedTokenIds) {
		Location location = Location.of(unexpectedToken);
		int unexpectedTokenId = unexpectedToken.sym;
		return new SyntaxError(Optional.ofNullable((ProgramElement) previousToken.value), location, unexpectedTokenId, expectedTokenIds);
	}

	@Override
	public String toString() {
		String expectedTokenNames = getExpectedTokenNames();
		if (expectedTokenNames.isEmpty() && !lastValidElement.isPresent()) {
			return "Unexpected " + unexpectedToken();
		} else if (expectedTokenNames.isEmpty()) {
			return "Unexpected " + unexpectedToken() + " following " + lastValidElement();
		} else if (!lastValidElement.isPresent()) {
			return "Expected " + expectedTokenNames + " but found " + unexpectedToken();
		} else {
			return "Expected " + expectedTokenNames + " following " + lastValidElement() + " but found " + unexpectedToken();
		}
	}

	private String lastValidElement() {
		return lastValidElement.get() + " at " + lastValidElement.get().getLocation();
	}

	private String unexpectedToken() {
		return terminalNames[unexpectedTokenId] + " at " + unexpectedTokenLocation;
	}

	@Override
	public Location getLocation() {
		if (lastValidElement.isPresent()) {
			Location.between(lastValidElement.get().getLocation(), unexpectedTokenLocation);
		}
		return unexpectedTokenLocation;
	}

	public String getExpectedTokenNames() {
		return expectedTokenIds.stream()
			.filter(id -> id != CUPSymbols.error)
			.map(id -> terminalNames[id])
			.collect(joining(" or "));
	}
}
