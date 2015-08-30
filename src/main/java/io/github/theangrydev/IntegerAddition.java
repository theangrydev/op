package io.github.theangrydev;

public class IntegerAddition implements Addition<Integer> {
	private final Expression<Integer> left;
	private final Expression<Integer> right;

	private IntegerAddition(Expression<Integer> left, Expression<Integer> right) {
		this.left = left;
		this.right = right;
	}

	public static IntegerAddition add(Expression<Integer> left, Expression<Integer> right) {
		return new IntegerAddition(left, right);
	}

	@Override
	public Expression<Integer> getLeft() {
		return left;
	}

	@Override
	public Expression<Integer> getRight() {
		return right;
	}

	@Override
	public void accept(Visitor<Integer> visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return left + "+" + right;
	}
}
