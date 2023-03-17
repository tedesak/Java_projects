package expression.generic;

import expression.ToMiniString;

public interface GenericExpressionEl<E extends GenericNumber> extends ToMiniString, GenericTripleExpression<E> {
    int getPriority();
}
