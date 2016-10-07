/*
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
