package io.github.theangrydev.op.language.expression;

import io.github.theangrydev.op.generation.ConstantReference;
import io.github.theangrydev.op.generation.ProgramCompiler;
import io.github.theangrydev.op.generation.StringType;
import io.github.theangrydev.op.generation.TypeReference;
import io.github.theangrydev.opper.scanner.Location;

import java.util.Optional;

import static io.github.theangrydev.op.generation.StringType.STRING_TYPE;

public class StringConstant implements Constant<String>, SimplifyingConstantAddition {
	private final Location location;
	private final String value;
	private ConstantReference<StringType> constantReference;

	private StringConstant(Location location, String value) {
		this.location = location;
		this.value = value;
	}

	public static StringConstant quotedStringConstant(Location location, String value) {
		return stringConstant(location, value.substring(1, value.length() - 1));
	}

	public static StringConstant stringConstant(Location location, String value) {
		return new StringConstant(location, value);
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
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
	public void checkTypes(ProgramCompiler programCompiler) {
		constantReference = programCompiler.registerStringConstant(value);
	}

	@Override
	public void compile(ProgramCompiler programCompiler) {
		underlyingType().load(programCompiler, constantReference);
	}

	@Override
	public Optional<Expression> simplifyAddToRight(Expression right) {
		return right.simplifyAddToLeft(this);
	}

	@Override
	public Expression addToLeft(RealConstant left) {
		return concatenateToLeft(left);
	}

	@Override
	public Expression addToLeft(IntegerConstant left) {
		return concatenateToLeft(left);
	}

	@Override
	public Expression addToLeft(StringConstant left) {
		return concatenateToLeft(left);
	}

	public Expression concatenateToLeft(Constant<?> left) {
		return stringConstant(locationBetween(left, this), left.toString() + value);
	}

	public Expression concatenateToRight(Constant<?> right) {
		return stringConstant(locationBetween(this, right), value + right.toString());
	}

	@Override
	public StringType underlyingType() {
		return STRING_TYPE;
	}

	@Override
	public TypeReference typeReference() {
		return constantReference;
	}
}