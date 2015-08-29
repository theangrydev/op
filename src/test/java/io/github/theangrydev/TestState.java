package io.github.theangrydev;

public class TestState extends com.googlecode.yatspec.state.givenwhenthen.TestState {

	public void addToGivens(String identifier, Object value) {
		interestingGivens.add(identifier, value);
	}
}
