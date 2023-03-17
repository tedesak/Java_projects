package expression.exceptions;

import expression.CheckedExpressionEl;

public class CheckedReverse extends AbstractCheckedUnaryOperation {
    public CheckedReverse(CheckedExpressionEl exp) {
        super(exp);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calculateReverse(exp.evaluate(x, y, z));
    }

    int calculateReverse(int x) {
        final int UPPER_TRESHOLD = Integer.MAX_VALUE / 10,
                LOWER_TRESHOLD = Integer.MIN_VALUE / 10;
        int y = 0;
        for (; x != 0; x /= 10) {
            if (y < LOWER_TRESHOLD || y > UPPER_TRESHOLD ||
                    y > 0 && Integer.MAX_VALUE - 10 * y < x % 10 ||
                     y < 0 && Integer.MIN_VALUE - 10 * y > x % 10) {
                throw new OverflowException("overflow\nException expression: " + toMiniString());
            }
            y = y * 10 + x % 10;
        }
        return y;
    }

    @Override
    public String toString() {
        return super.toString("reverse");
    }

    @Override
    public String toMiniString() {
        return super.toMiniString("reverse");
    }
}
