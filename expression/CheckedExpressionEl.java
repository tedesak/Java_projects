package expression;

public interface CheckedExpressionEl extends TripleExpression, ToMiniString {
    int getPriority();
}
