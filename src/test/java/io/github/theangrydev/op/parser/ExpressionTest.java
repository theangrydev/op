package io.github.theangrydev.op.parser;

import io.github.theangrydev.WithAssertions;
import org.junit.Test;
import org.reflections.Reflections;

import java.lang.reflect.Executable;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ExpressionTest implements WithAssertions {

	@Test
	public void shouldAcceptVisitorsForAllTheImplementationsOfExpression() {
		Collection<Class<?>> leafExpressionTypes = leafExpressionTypes();
		List<Class<?>> acceptedTypes = acceptedTypes();
		assertThat(acceptedTypes).containsAll(leafExpressionTypes);
	}

	private Collection<Class<?>> leafExpressionTypes() {
		Reflections reflections = new Reflections("io.github.theangrydev");
		Set<Class<? extends Expression>> subTypesOfExpression = reflections.getSubTypesOf(Expression.class);
		return subTypesOfExpression.stream()
			.filter(expression -> reflections.getSubTypesOf(expression).isEmpty())
			.collect(Collectors.toList());
	}

	private List<Class<?>> acceptedTypes() {
		return Arrays.stream(Expression.Visitor.class.getMethods())
			.map(Executable::getParameters)
			.map(parameters -> parameters[0])
			.map(Parameter::getType)
			.collect(Collectors.toList());
	}
}
