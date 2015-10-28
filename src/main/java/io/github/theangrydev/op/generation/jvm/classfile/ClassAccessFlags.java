package io.github.theangrydev.op.generation.jvm.classfile;

import io.github.theangrydev.op.generation.jvm.ClassFileWriter;

import java.io.DataOutput;
import java.io.IOException;

public class ClassAccessFlags implements ClassFileWriter {

	private short flags;

	@Override
	public void writeTo(DataOutput dataOutput) throws IOException {
		dataOutput.writeShort(flags);
	}

	public void markPublic() {
		flags |= 0x0001;
	}

	public void treatSuperClassMethodsSpecially() {
		flags |= 0x0020;
	}
}
