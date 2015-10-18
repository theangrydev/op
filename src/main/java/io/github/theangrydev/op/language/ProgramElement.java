package io.github.theangrydev.op.language;

import io.github.theangrydev.opper.scanner.Location;

public interface ProgramElement<T extends ProgramElement<T>> {
	Location getLocation();
	T simplify();

	static Location locationBetween(ProgramElement start, ProgramElement end) {
		return Location.between(start.getLocation(), end.getLocation());
	}
}
