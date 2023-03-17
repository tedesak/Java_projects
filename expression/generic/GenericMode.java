package expression.generic;

public class GenericMode<E extends GenericNumber> extends AbstractGenericBinOperation<E> {
    public GenericMode(GenericExpressionEl<E> exp1, GenericExpressionEl<E> exp2) {
        super(exp1, exp2, 2, true, true, false);
    }

    @Override
    protected E evaluateImpl(E x, E y) {
        return (E) x.mode(y);
    }

    @Override
    protected String opString() {
        return "mod";
    }
}
