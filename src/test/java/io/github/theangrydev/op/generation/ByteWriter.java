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
package io.github.theangrydev.op.generation;

import io.github.theangrydev.op.generation.jvm.ClassFileWriter;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import static java.util.Arrays.stream;

public class ByteWriter {

	public static byte[] bytesWrittenBy(ClassFileWriter... classFileWriters) throws IOException {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream dataOutput = new DataOutputStream(byteStream);
		for (ClassFileWriter classFileWriter : classFileWriters) {
			classFileWriter.writeTo(dataOutput);
		}
		return byteStream.toByteArray();
	}

	public static byte[] bytes(byte first, byte... rest) {
		return bytes(new byte[]{first}, rest);
	}

	public static byte[] bytes(byte[]... byteArrays) {
		byte[] joined = new byte[stream(byteArrays).mapToInt(x -> x.length).sum()];
		int offset = 0;
		for (byte[] byteArray : byteArrays) {
			System.arraycopy(byteArray, 0, joined, offset, byteArray.length);
			offset += byteArray.length;
		}
		return joined;
	}
}
