package expression.generic;

public class GenericSquare<E extends GenericNumber> extends AbstractGenericUnaryOperation<E> {
    public GenericSquare(GenericExpressionEl<E> exp) {
        super(exp);
    }

    @Override
    protected E evaluateImpl(E ev) {
        return (E) ev.square();
    }

    @Override
    protected String opString() {
        return "sqr";
    }
}
