package io.github.theangrydev.op.parser;

public class Addition implements BinaryOperator {

	private final Expression left;
	private final Expression right;

	private Addition(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	public static Addition add(Expression left, Expression right) {
		return new Addition(left, right);
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
