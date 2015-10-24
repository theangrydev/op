package io.github.theangrydev.op.language.expression;

import io.github.theangrydev.op.generation.ConstantReference;
import io.github.theangrydev.op.generation.ProgramCompiler;
import io.github.theangrydev.op.generation.TypeReference;
import io.github.theangrydev.op.generation.UnderlyingType;
import io.github.theangrydev.opper.scanner.Location;

import java.util.Optional;

import static io.github.theangrydev.op.generation.UnderlyingType.REAL;
import static java.lang.Double.parseDouble;

public class RealConstant implements NumericConstant<Double>, SimplifyingConstantAddition {
	private final Location location;
	private final double value;
	private ConstantReference constantReference;

	private RealConstant(Location location, Double value) {
		this.location = location;
		this.value = value;
	}

	public static RealConstant realConstant(Location location, String value) {
		return realConstant(location, parseDouble(value));
	}

	public static RealConstant realConstant(Location location, double value) {
		return new RealConstant(location, value);
	}

	@Override
	public Double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
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
		constantReference = programCompiler.registerRealConstant(value);
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
	public Optional<Expression> simplifyAddToLeft(TypeExpression left) {
		return left.simplifyAddTypeToRight(this);
	}

	@Override
	public Expression addToLeft(RealConstant left) {
		return addRealToLeft(left);
	}

	@Override
	public Expression addToLeft(IntegerConstant left) {
		return addRealToLeft(left);
	}

	@Override
	public Expression addToLeft(StringConstant left) {
		return left.concatenateToRight(this);
	}

	public RealConstant addRealToLeft(NumericConstant<?> left) {
		return realConstant(locationBetween(left, this), left.getValue().doubleValue() + value);
	}

	public RealConstant addRealToRight(NumericConstant<?> right) {
		return realConstant(locationBetween(this, right), value + right.getValue().doubleValue());
	}

	@Override
	public boolean isZero() {
		return value == 0.0d;
	}

	@Override
	public UnderlyingType underlyingType() {
		return REAL;
	}

	@Override
	public TypeReference typeReference() {
		return constantReference;
	}
}
