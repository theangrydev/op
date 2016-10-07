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
package io.github.theangrydev.op.scanner;

import com.google.common.base.Stopwatch;
import io.github.theangrydev.op.parser.ProgramScannerFactory;
import io.github.theangrydev.opper.scanner.Scanner;
import org.junit.Test;

import java.io.CharArrayReader;
import java.io.IOException;
import java.util.Arrays;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.assertj.core.api.Assertions.assertThat;

public class ScannerPerformanceTest {

	public static final int COUNT = 10000;

	@Test
	public void shouldScanInAReasonableTime() throws IOException {
		Scanner scanner = ProgramScannerFactory.PROGRAM_SCANNER_FACTORY.scanner(new CharArrayReader(characters(COUNT)));
		Stopwatch stopwatch = Stopwatch.createStarted();
		for (int i = 0; i < COUNT; i++) {
			scanner.hasNextSymbol();
		}

		assertThat(stopwatch.elapsed(MILLISECONDS)).describedAs("Time taken should be less than 100ms").isLessThan(100);
	}

	private char[] characters(int numberOfCharacters) {
		char[] chars = new char[numberOfCharacters];
		Arrays.fill(chars, '&');
		return chars;
	}

}
