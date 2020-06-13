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

import java.util.Optional;

public interface SimplifyingConstantAddition extends AdditionSimplifier {
	default Optional<Expression> simplifyAddToLeft(RealConstant left) {
		return Optional.of(addToLeft(left));
	}

	default Optional<Expression> simplifyAddToLeft(IntegerConstant left) {
		return Optional.of(addToLeft(left));
	}

	default Optional<Expression> simplifyAddToLeft(StringConstant left) {
		return Optional.of(addToLeft(left));
	}

	Expression addToLeft(RealConstant left);
	Expression addToLeft(IntegerConstant left);
	Expression addToLeft(StringConstant left);
}
