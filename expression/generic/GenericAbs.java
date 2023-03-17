package expression.generic;

public class GenericAbs<E extends GenericNumber> extends AbstractGenericUnaryOperation<E> {
    public GenericAbs(GenericExpressionEl<E> exp) {
        super(exp);
    }

    @Override
    public E evaluateImpl(E ev) {
        return (E) ev.abs();
    }

    @Override
    protected String opString() {
        return "abs";
    }
}
