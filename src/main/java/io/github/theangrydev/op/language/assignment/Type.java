package io.github.theangrydev.op.language.assignment;

import com.google.common.base.Preconditions;
import io.github.theangrydev.op.generation.ProgramCompiler;
import io.github.theangrydev.op.generation.UnderlyingType;
import io.github.theangrydev.op.language.ProgramElement;
import io.github.theangrydev.opper.scanner.Location;

import java.util.Optional;

public class Type implements ProgramElement<Type> {
	private final Location location;
	private final String type;
	private UnderlyingType<?> underlyingType;

	private Type(Location location, String type) {
		this.location = location;
		this.type = type;
	}

	public static Type type(Location location, String type) {
		return new Type(location, type);
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return type;
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public Type simplify() {
		return this;
	}

	@Override
	public void checkTypes(ProgramCompiler programCompiler) {
		Optional<UnderlyingType<?>> underlyingType = programCompiler.underlyingType(type);
		Preconditions.checkState(underlyingType.isPresent(), "Type '%s' does not exist", type);
		this.underlyingType = underlyingType.get();
	}

	@Override
	public void compile(ProgramCompiler programCompiler) {

	}

	public UnderlyingType<?> underlyingType() {
		return underlyingType;
	}
}
