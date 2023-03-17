package expression.exceptions;

import expression.CheckedExpressionEl;

public class CheckedMultiply extends  AbstractCheckedBinOperation {
    public CheckedMultiply(CheckedExpressionEl exp1, CheckedExpressionEl exp2) {
        super(exp1, exp2, 2, true, true, false);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int ev1 = exp1.evaluate(x, y, z),
                ev2 = exp2.evaluate(x, y, z),
                ans = ev1 * ev2;
        if (ev2 != 0 && ans / ev2 != ev1 ||
                        ev1 == Integer.MIN_VALUE && ev2 == -1) {
            throw new OverflowException("overflow\nException expression: " + toMiniString());
        }
        return ans;
    }

    @Override
    public String toString() {
        return super.toString("*");
    }

    @Override
    public String toMiniString() {
        return super.toMiniString("*");
    }
}
