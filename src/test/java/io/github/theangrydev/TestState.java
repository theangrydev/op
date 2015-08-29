package io.github.theangrydev;

public class TestState extends com.googlecode.yatspec.state.givenwhenthen.TestState {

	public void store(String identifier, Object value) {
		interestingGivens.add(identifier, value);
	}

	public final <R> R retrieve(String identifier, Class<R> aClass) {
		return interestingGivens.getType(identifier, aClass);
	}
}
