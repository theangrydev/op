package io.github.theangrydev;

public interface Expression {
	void accept(Visitor visitor);
	interface Visitor {
		void visit(IntegerConstant integerConstant);
		void visit(RealConstant realConstant);
		void visit(StringConstant stringConstant);
		void visit(TypeExpression typeExpression);
		void visit(IntegerAddition integerAddition);
		void visit(TypeAddition typeAddition);
	}
}
