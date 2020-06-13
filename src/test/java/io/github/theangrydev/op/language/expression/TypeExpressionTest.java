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

import io.github.theangrydev.op.common.WithAssertions;
import org.junit.Test;

public class TypeExpressionTest implements WithAssertions {

	@Test
	public void shouldStoreTheValue() throws Exception {
		assertThat(TypeExpression.typeExpression(null, "ABC")).hasType("ABC");
	}

	@Test
	public void shouldToStringTheValue() throws Exception {
		assertThat(TypeExpression.typeExpression(null, "ABC")).hasToString("ABC");
	}
}
