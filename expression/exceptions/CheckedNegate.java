package expression.exceptions;

import expression.CheckedExpressionEl;

public class CheckedNegate extends AbstractCheckedUnaryOperation {
    public CheckedNegate(CheckedExpressionEl exp) {
        super(exp);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int ans = exp.evaluate(x, y, z);
        if (ans == Integer.MIN_VALUE) {
            throw new OverflowException("overflow\nException expression: " + toMiniString());
        }
        return -exp.evaluate(x, y, z);
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
