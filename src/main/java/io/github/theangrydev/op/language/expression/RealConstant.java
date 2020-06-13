/*
 * Copyright 2015-2020 Liam Williams <liam.williams@zoho.com>.
 *
 * This file is part of op.
 *
 * op is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * op is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with op.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.theangrydev.op.language.expression;

import io.github.theangrydev.op.generation.ConstantReference;
import io.github.theangrydev.op.generation.ProgramCompiler;
import io.github.theangrydev.op.generation.RealType;
import io.github.theangrydev.op.generation.TypeReference;
import io.github.theangrydev.opper.scanner.Location;

import java.util.Optional;

import static io.github.theangrydev.op.generation.RealType.REAL_TYPE;
import static java.lang.Double.parseDouble;

public class RealConstant implements NumericConstant<Double>, SimplifyingConstantAddition {
	private final Location location;
	private final double value;
	private ConstantReference<RealType> constantReference;

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
		constantReference.load(programCompiler);
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
	public RealType underlyingType() {
		return REAL_TYPE;
	}

	@Override
	public TypeReference<RealType> typeReference() {
		return constantReference;
	}
}
