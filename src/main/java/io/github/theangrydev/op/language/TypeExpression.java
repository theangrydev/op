package io.github.theangrydev.op.language;

import io.github.theangrydev.opper.scanner.Location;

import java.util.Optional;

public class TypeExpression implements Expression {
	private final Location location;
	private final String type;

	private TypeExpression(Location location, String type) {
		this.location = location;
		this.type = type;
	}

	public static TypeExpression of(Location location, String type) {
		return new TypeExpression(location, type);
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
	public Expression simplify() {
		return this;
	}

	@Override
	public Optional<Expression> addToRight(Expression right) {
		return Optional.empty();
	}

	@Override
	public Optional<Expression> addToLeft(RealConstant left) {
		return Optional.empty();
	}

	@Override
	public Optional<Expression> addToLeft(IntegerConstant left) {
		return Optional.empty();
	}

	@Override
	public Optional<Expression> addToLeft(StringConstant left) {
		return Optional.empty();
	}
}
