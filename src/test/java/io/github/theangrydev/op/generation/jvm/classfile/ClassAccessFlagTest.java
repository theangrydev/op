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
