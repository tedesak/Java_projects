package expression.generic;

public class GenericAdd<E extends GenericNumber> extends AbstractGenericBinOperation<E> {
    public GenericAdd(GenericExpressionEl<E> exp1, GenericExpressionEl<E> exp2) {
        super(exp1, exp2, 1, true, true, false);
    }

    @Override
    protected E evaluateImpl(E x, E y) {
        return (E) x.add(y);
    }

    @Override
    protected String opString() {
        return "+";
    }
}
