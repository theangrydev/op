package io.github.theangrydev.op.language;

import io.github.theangrydev.op.generation.ProgramCompiler;
import io.github.theangrydev.opper.scanner.Location;

public interface ProgramElement<T extends ProgramElement<T>> {
	Location getLocation();
	T simplify();
	void checkTypes(ProgramCompiler programCompiler);
	void compile(ProgramCompiler programCompiler);

	default Location locationBetween(ProgramElement start, ProgramElement end) {
		return Location.between(start.getLocation(), end.getLocation());
	}
}
