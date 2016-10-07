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
package io.github.theangrydev.op.generation.jvm.classfile;

import com.googlecode.yatspec.junit.Row;
import com.googlecode.yatspec.junit.Table;
import com.googlecode.yatspec.junit.TableRunner;
import io.github.theangrydev.op.common.WithAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.github.theangrydev.op.common.Integers.decimalValue;
import static io.github.theangrydev.op.generation.jvm.classfile.ClassAccessFlag.classAccessFlag;

@RunWith(TableRunner.class)
public class ClassAccessFlagTest implements WithAssertions {

	@Table({
		@Row({"PUBLIC", "0x0001"}),
		@Row({"TREAT_SUPER_METHODS_SPECIALLY", "0x0020"}),
	})
	@Test
	public void flagsMatchTheJvmSpecification(String flag, String value) {
		assertThat(classAccessFlag(flag).value()).isEqualTo(decimalValue(value));
	}
}
