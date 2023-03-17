package expression.generic;

public class GenericMultiply<E extends GenericNumber> extends AbstractGenericBinOperation<E> {
    public GenericMultiply(GenericExpressionEl<E> exp1, GenericExpressionEl<E> exp2) {
        super(exp1, exp2, 2, true, true, false);
    }

    @Override
    protected E evaluateImpl(E x, E y) {
        return (E) x.multiply(y);
    }

    @Override
    protected String opString() {
        return "*";
    }
}
