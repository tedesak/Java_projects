package expression.exceptions;

import expression.CheckedExpressionEl;

public class CheckedDivide extends AbstractCheckedBinOperation {
    public CheckedDivide(final CheckedExpressionEl exp1, final CheckedExpressionEl exp2) {
        super(exp1, exp2, 2, false, false, false);
    }

    @Override
    public int evaluate(final int x, final int y, final int z) {
        final int ev1 = exp1.evaluate(x, y, z);
        final int ev2 = exp2.evaluate(x ,y ,z);
        if (ev2 == 0) {
            throw new DivisionByZeroException("division by zero\nException expression: " + toMiniString());
        }
        if (ev1 == Integer.MIN_VALUE && ev2 == -1) {
            throw new OverflowException("overflow\nException expression: " + toMiniString());
        }
        return ev1 / ev2;
    }

    @Override
    public String toString() {
        return super.toString("/");
    }

    @Override
    public String toMiniString() {
        return super.toMiniString("/");
    }
}
