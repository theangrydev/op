package io.github.theangrydev;

public class TypeAddition implements Addition<Object> {

	private final TypeExpression left;
	private final TypeExpression right;

	public TypeAddition(TypeExpression left, TypeExpression right) {
		this.left = left;
		this.right = right;
	}

	public static TypeAddition add(TypeExpression left, TypeExpression right) {
		return new TypeAddition(left, right);
	}

	@Override
	public TypeExpression getLeft() {
		return left;
	}

	@Override
	public TypeExpression getRight() {
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
