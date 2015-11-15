/**
 * Copyright 2015 Liam Williams <liam.williams@zoho.com>.
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
import io.github.theangrydev.op.generation.IntegerType;
import io.github.theangrydev.op.generation.ProgramCompiler;
import io.github.theangrydev.op.generation.TypeReference;
import io.github.theangrydev.opper.scanner.Location;

import java.util.Optional;

import static io.github.theangrydev.op.generation.IntegerType.INTEGER_TYPE;
import static java.lang.Integer.parseInt;

public class IntegerConstant implements NumericConstant<Integer>, SimplifyingConstantAddition {
	private final Location location;
	private final int value;
	private ConstantReference<IntegerType> constantReference;

	private IntegerConstant(Location location, int value) {
		this.location = location;
		this.value = value;
	}

	public static IntegerConstant integerConstant(Location location, String value) {
		return integerConstant(location, parseInt(value));
	}

	public static IntegerConstant integerConstant(Location location, int value) {
		return new IntegerConstant(location, value);
	}

	@Override
	public Integer getValue() {
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
		constantReference = programCompiler.registerIntegerConstant(value);
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
		return left.addRealToRight(this);
	}

	@Override
	public Expression addToLeft(IntegerConstant left) {
		return integerConstant(locationBetween(left, this), value + left.getValue());
	}

	@Override
	public Expression addToLeft(StringConstant left) {
		return left.concatenateToRight(this);
	}

	@Override
	public boolean isZero() {
		return value == 0;
	}

	@Override
	public IntegerType underlyingType() {
		return INTEGER_TYPE;
	}

	@Override
	public TypeReference typeReference() {
		return constantReference;
	}
}
