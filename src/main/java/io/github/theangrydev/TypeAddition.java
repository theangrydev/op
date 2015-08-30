package io.github.theangrydev;

public class TypeAddition implements Addition<Object> {

	private final Expression<Object> left;
	private final Expression<Object> right;

	public TypeAddition(Expression<Object> left, Expression<Object> right) {
		this.left = left;
		this.right = right;
	}

	public static TypeAddition add(Expression<Object> left, Expression<Object> right) {
		return new TypeAddition(left, right);
	}

	@Override
	public Expression<Object> getLeft() {
		return left;
	}

	@Override
	public Expression<Object> getRight() {
		return right;
	}

	@Override
	public void accept(Visitor<Object> visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return left + "+" + right;
	}
}
