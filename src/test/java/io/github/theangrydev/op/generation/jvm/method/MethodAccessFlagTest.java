package io.github.theangrydev.op.generation.jvm.method;

import com.googlecode.yatspec.junit.Row;
import com.googlecode.yatspec.junit.Table;
import com.googlecode.yatspec.junit.TableRunner;
import io.github.theangrydev.op.common.WithAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.github.theangrydev.op.common.Integers.decimalValue;
import static io.github.theangrydev.op.generation.jvm.method.MethodAccessFlag.methodAccessFlag;

@RunWith(TableRunner.class)
public class MethodAccessFlagTest implements WithAssertions {

	@Table({
		@Row({"PUBLIC", "0x0001"}),
		@Row({"STATIC", "0x0008"}),
	})
	@Test
	public void flagsMatchTheJvmSpecification(String flag, String value) {
		assertThat(methodAccessFlag(flag).value()).isEqualTo(decimalValue(value));
	}
}
