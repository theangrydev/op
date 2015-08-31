package io.github.theangrydev.op.parser;

import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;

import static io.github.theangrydev.op.parser.CUPSymbols.terminalNames;

public class UnexpectedToken implements ProgramElement {
	private final Location location;
	private final int tokenId;

	private UnexpectedToken(Location location, int tokenId) {
		this.location = location;
		this.tokenId = tokenId;
	}

	public static UnexpectedToken of(ComplexSymbol complexSymbol) {
		Location location = Location.of(complexSymbol);
		int tokenId = complexSymbol.sym;
		return new UnexpectedToken(location, tokenId);
	}

	@Override
	public String toString() {
		return terminalNames[tokenId] + " at " + location;
	}

	@Override
	public Location getLocation() {
		return location;
	}
}
