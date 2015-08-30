package io.github.theangrydev.op.parser;

public interface Expression {
	void accept(Visitor visitor);
	interface Visitor {
		void visit(IntegerConstant integerConstant);
		void visit(RealConstant realConstant);
		void visit(StringConstant stringConstant);
		void visit(TypeExpression typeExpression);
		void visit(Addition addition);
	}
}
