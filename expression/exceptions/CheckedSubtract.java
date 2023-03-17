package expression.exceptions;

import expression.CheckedExpressionEl;

public class CheckedSubtract extends AbstractCheckedBinOperation {
    public CheckedSubtract(CheckedExpressionEl exp1, CheckedExpressionEl exp2) {
        super(exp1, exp2, 1, true, false, false);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int ev1 = exp1.evaluate(x, y, z),
                ev2 = exp2.evaluate(x, y, z);
        if (ev2 < 0 && ev1 > Integer.MAX_VALUE + ev2 || ev2 > 0 && ev1 < Integer.MIN_VALUE + ev2) {
            throw new OverflowException("overflow\nException expression: " + toMiniString());
        }
        return ev1 - ev2;
    }

    @Override
    public String toString() {
        return super.toString("-");
    }

    @Override
    public String toMiniString() {
        return super.toMiniString("-");
    }
}
