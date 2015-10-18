package io.github.theangrydev.op.scanner;

import com.google.common.base.Stopwatch;
import io.github.theangrydev.op.parser.ProgramGrammar;
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
		Scanner scanner = ProgramScannerFactory.programScannerFactory(ProgramGrammar.programGrammar()).scanner(new CharArrayReader(characters(COUNT)));
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
