package expression.generic;

import java.util.Objects;

public abstract class AbstractGenericUnaryOperation<E extends GenericNumber> implements GenericExpressionEl<E> {
    protected final GenericExpressionEl<E> exp;

    public AbstractGenericUnaryOperation(GenericExpressionEl<E> exp) {
        this.exp = exp;
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE - 1;
    }

    @Override
    public String toString() {
        return opString() + "(" + exp.toString() + ")";
    }

    @Override
    public String toMiniString() {
        String opName = opString();
        if (getPriority() > exp.getPriority()) {
            return opName + "(" + exp.toMiniString() + ")";
        } else {
            return opName + " " + exp.toMiniString();
        }
    }

    @Override
    public E evaluate(int x, int y, int z) {
        E ev = exp.evaluate(x, y, z);
        return evaluateImpl(ev);
    }

    protected abstract E evaluateImpl(E ev);

    protected abstract String opString();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractGenericUnaryOperation<?> that = (AbstractGenericUnaryOperation<?>) o;
        return Objects.equals(exp, that.exp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exp);
    }
}
