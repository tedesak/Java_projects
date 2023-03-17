package expression.exceptions;

import expression.CheckedExpressionEl;

import java.util.Objects;

abstract public class AbstractCheckedUnaryOperation implements CheckedExpressionEl {
    protected final CheckedExpressionEl exp;

    public AbstractCheckedUnaryOperation(CheckedExpressionEl exp) {
        this.exp = exp;
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE - 1;
    }

    protected String toString(String c) {
        return c + "(" + exp.toString() + ")";
    }

    protected String toMiniString(String c) {
        if (getPriority() > exp.getPriority()) {
            return c + "(" + exp.toMiniString() + ")";
        } else {
            return c + " " + exp.toMiniString();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractCheckedUnaryOperation that = (AbstractCheckedUnaryOperation) o;
        return exp.equals(that.exp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exp);
    }
}
