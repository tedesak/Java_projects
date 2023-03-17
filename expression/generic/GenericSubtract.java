package expression.generic;

public class GenericSubtract<E extends GenericNumber> extends AbstractGenericBinOperation<E> {
    public GenericSubtract(GenericExpressionEl<E> exp1, GenericExpressionEl<E> exp2) {
        super(exp1, exp2, 1, true, false, false);
    }

    @Override
    protected E evaluateImpl(E x, E y) {
        return (E) x.subtract(y);
    }

    @Override
    protected String opString() {
        return "-";
    }
}
