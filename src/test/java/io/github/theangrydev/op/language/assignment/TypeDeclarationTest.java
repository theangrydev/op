/*
 * Copyright 2015-2016 Liam Williams <liam.williams@zoho.com>.
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

import io.github.theangrydev.op.common.WithAssertions;
import io.github.theangrydev.op.language.expression.TypeExpression;
import io.github.theangrydev.opper.scanner.Location;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class TypeDeclarationTest implements WithAssertions {

	private final Location location = mock(Location.class);
	private final TypeExpression target = TypeExpression.typeExpression(location, "target");
	private final Type existing = Type.type(location, "existing");

	@Test
	public void shouldStoreTargetAndExistingType() throws Exception {
		assertThat(TypeDeclaration.of(target, existing))
			.hasTargetType(target)
			.hasExistingType(existing);
	}

	@Test
	public void shouldToStringTheValue() throws Exception {
		assertThat(TypeDeclaration.of(target, existing)).hasToString("target:existing");
	}
}
