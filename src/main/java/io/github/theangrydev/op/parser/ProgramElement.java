package io.github.theangrydev.op.parser;

import io.github.theangrydev.opper.scanner.Location;

public interface ProgramElement {
	Location getLocation();

	static Location locationBetween(ProgramElement start, ProgramElement end) {
		return Location.between(start.getLocation(), end.getLocation());
	}
}
