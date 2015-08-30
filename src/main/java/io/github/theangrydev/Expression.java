package io.github.theangrydev;

public interface Expression<T> {
	void accept(Visitor<T> visitor);
	interface Visitor<T> {
		void visit(IntegerConstant integerConstant);
		void visit(RealConstant realConstant);
		void visit(StringConstant stringConstant);
		void visit(TypeExpression<T> typeExpression);
		void visit(IntegerAddition integerAddition);
		void visit(TypeAddition typeAddition);
	}
}
