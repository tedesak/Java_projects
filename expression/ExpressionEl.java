package expression;

public interface ExpressionEl extends Expression, DoubleExpression, TripleExpression {
    public int getPriority();
}
