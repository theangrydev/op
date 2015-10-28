package io.github.theangrydev.op.generation.jvm;

import java.io.DataOutput;
import java.io.IOException;

public interface ClassFileWriter {
	void writeTo(DataOutput dataOutput) throws IOException;
}
