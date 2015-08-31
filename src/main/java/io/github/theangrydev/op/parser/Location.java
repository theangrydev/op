package io.github.theangrydev.op.parser;

import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;

public class Location {

	private final ComplexSymbolFactory.Location left;
	private final ComplexSymbolFactory.Location right;

	private Location(ComplexSymbolFactory.Location left, ComplexSymbolFactory.Location right) {
		this.left = left;
		this.right = right;
	}

	public static Location of(ComplexSymbol complexSymbol) {
		return between(complexSymbol.getLeft(), complexSymbol.getRight());
	}

	public static Location between(ComplexSymbolFactory.Location left, ComplexSymbolFactory.Location right) {
		return new Location(left, right);
	}

	public static Location between(ProgramElement left, ProgramElement right) {
		return between(left.getLocation(), right.getLocation());
	}

	public static Location between(Location left, Location right) {
		return new Location(left.left, right.right);
	}

	@Override
	public String toString() {
		return render(left) + '-' + render(right);
	}

	private String render(ComplexSymbolFactory.Location location) {
		if (location == null) {
			return "null";
		}
		return location.getUnit() + '[' + location.getLine() + ':' + location.getColumn() + ']';
	}

	public String getRight() {
		return render(right);
	}

	public String getLeft() {
		return render(left);
	}
}
