package io.github.theangrydev;

public class IntegerAddition implements Addition<Integer> {
	private final Expression left;
	private final Expression right;

	private IntegerAddition(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	public static IntegerAddition add(Expression left, Expression right) {
		return new IntegerAddition(left, right);
	}

	@Override
	public Expression getLeft() {
		return left;
	}

	@Override
	public Expression getRight() {
		return right;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return left + "+" + right;
	}
}
