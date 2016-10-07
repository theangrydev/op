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
package io.github.theangrydev.op.language.expression;

import io.github.theangrydev.op.common.WithAssertions;
import io.github.theangrydev.opper.scanner.Location;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class AdditionTest implements WithAssertions {

	private final Location location = mock(Location.class);

	@Test
	public void shouldStoreLeftAndRight() throws Exception {
		TypeExpression left = TypeExpression.typeExpression(location, "A");
		TypeExpression right = TypeExpression.typeExpression(location, "B");
		assertThat(Addition.add(left, right)).hasLeft(left).hasRight(right);
	}

	@Test
	public void shouldToStringTheValue() throws Exception {
		TypeExpression left = TypeExpression.typeExpression(location, "A");
		TypeExpression right = TypeExpression.typeExpression(location, "B");
		assertThat(Addition.add(left, right)).hasToString("A+B");
	}

	//TODO: fix test
	@Ignore
	@Test
	public void shouldStoreTheLocation() throws Exception {
		TypeExpression left = TypeExpression.typeExpression(location, "A");
		TypeExpression right = TypeExpression.typeExpression(location, "B");
		assertThat(Addition.add(left, right)).hasLocation(location);
	}
}
