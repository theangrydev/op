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
package io.github.theangrydev.op.generation.jvm.method;

import io.github.theangrydev.op.generation.jvm.Flag;

public enum MethodAccessFlag implements Flag {
	PUBLIC(0x0001),
	STATIC(0x0008);

	private final int value;

	MethodAccessFlag(int value) {
		this.value = value;
	}

	public static MethodAccessFlag methodAccessFlag(String name) {
		return MethodAccessFlag.valueOf(name);
	}

	@Override
	public int value() {
		return value;
	}
}
