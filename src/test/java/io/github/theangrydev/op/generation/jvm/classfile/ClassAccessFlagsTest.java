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
package io.github.theangrydev.op.generation.jvm.classfile;

import io.github.theangrydev.op.common.WithAssertions;
import org.junit.Test;

import java.io.IOException;

import static io.github.theangrydev.op.generation.ByteWriter.bytesWrittenBy;
import static io.github.theangrydev.op.generation.jvm.ShortValue.shortValue;
import static io.github.theangrydev.op.generation.jvm.classfile.ClassAccessFlag.PUBLIC;
import static io.github.theangrydev.op.generation.jvm.classfile.ClassAccessFlag.TREAT_SUPER_METHODS_SPECIALLY;
import static io.github.theangrydev.op.generation.jvm.classfile.ClassAccessFlags.classAccessFlags;

public class ClassAccessFlagsTest implements WithAssertions {

	@Test
	public void writesSetFlagsAsAnInteger() throws IOException {
		assertThat(bytesWrittenBy(classAccessFlags(PUBLIC, TREAT_SUPER_METHODS_SPECIALLY))).containsExactly(bytesWrittenBy(shortValue(PUBLIC.value() | TREAT_SUPER_METHODS_SPECIALLY.value())));
	}
}
