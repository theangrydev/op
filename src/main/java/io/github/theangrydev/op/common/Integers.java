package io.github.theangrydev.op.common;

import static java.lang.Integer.parseInt;

public class Integers {

	public static int decimalValue(String hexadecimalValue) {
		return parseInt(hexadecimalValue.substring(2), 16);
	}
}
