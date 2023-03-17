package expression;

public interface ExpressionEl extends Expression, DoubleExpression, CheckedExpressionEl {
    int getPriority();
}
