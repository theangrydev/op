package io.github.theangrydev.op.generation.jvm;

import static io.github.theangrydev.opper.common.Streams.stream;

public interface Flag {
	int value();
	static int combine(Iterable<? extends Flag> flags) {
		return stream(flags)
			.mapToInt(Flag::value)
			.reduce(0, (flagsSoFar, flagToOr) -> flagsSoFar | flagToOr);
	}
}
