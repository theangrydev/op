package io.github.theangrydev.op.parser;

import java_cup.runtime.ComplexSymbolFactory;

import java.util.Objects;

public class Location {

	private final ComplexSymbolFactory.Location left;
	private final ComplexSymbolFactory.Location right;

	private Location(ComplexSymbolFactory.Location left, ComplexSymbolFactory.Location right) {
		this.left = left;
		this.right = right;
	}

	public static Location between(ComplexSymbolFactory.Location left, ComplexSymbolFactory.Location right) {
		return new Location(left, right);
	}

	public static Location between(ProgramElement left, ProgramElement right) {
		return new Location(left.getLocation().left, right.getLocation().right);
	}

	private boolean containedInTheSameUnit() {
		return Objects.equals(left.getUnit(), right.getUnit());
	}

	private boolean containedInTheSameLine() {
		return left.getLine() == right.getLine();
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append('[');
		stringBuilder.append(left.getUnit());
		stringBuilder.append(' ');
		stringBuilder.append(left.getLine());
		stringBuilder.append(':');
		stringBuilder.append(left.getColumn());
		stringBuilder.append('-');
		if (!containedInTheSameUnit()) {
			stringBuilder.append(right.getUnit());
			stringBuilder.append(' ');
		}
		if (!containedInTheSameLine()) {
			stringBuilder.append(right.getLine());
			stringBuilder.append(':');
		}
		stringBuilder.append(right.getColumn());
		stringBuilder.append(']');
		return stringBuilder.toString();
	}
}
