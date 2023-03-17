package expression.generic;

public class GenericDivide<E extends GenericNumber> extends AbstractGenericBinOperation<E> {
    public GenericDivide(GenericExpressionEl<E> exp1, GenericExpressionEl<E> exp2) {
        super(exp1, exp2, 2, false, false, false);
    }

    @Override
    protected E evaluateImpl(E x, E y) {
        return (E) x.divide(y);
    }

    @Override
    protected String opString() {
        return "/";
    }
}
