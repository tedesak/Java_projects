package expression.exceptions;

import expression.CheckedExpressionEl;

public class CheckedPow extends AbstractCheckedUnaryOperation {
    public CheckedPow(CheckedExpressionEl exp) {
        super(exp);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return countPow10(exp.evaluate(x, y, z));
    }

    private int countPow10(int x) {
        if (x < 0) {
            throw new IllegalArgumentException("negative degree\nException expression: " + toMiniString());
        }
        final int THRESHOLD = Integer.MAX_VALUE / 10;
        int ans = 1;
        for (; x > 0; x--) {
            if (THRESHOLD < ans) {
                throw new OverflowException("overflow\nException expression: " + toMiniString());
            }
            ans *= 10;
        }
        return ans;
    }

    @Override
    public String toString() {
        return super.toString("pow10");
    }

    @Override
    public String toMiniString() {
        return super.toMiniString("pow10");
    }
}
