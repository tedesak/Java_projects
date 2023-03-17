package expression.generic;

public class GenericUnaryMinus<E extends GenericNumber> extends AbstractGenericUnaryOperation<E> {
    public GenericUnaryMinus(GenericExpressionEl<E> exp) {
        super(exp);
    }

    @Override
    protected E evaluateImpl(E ev) {
        return (E) ev.unaryMinus();
    }

    @Override
    protected String opString() {
        return "-";
    }
}
