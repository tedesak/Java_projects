package expression.exceptions;

import expression.CheckedExpressionEl;

public class CheckedGcd extends AbstractCheckedBinOperation {
    public CheckedGcd(CheckedExpressionEl exp1, CheckedExpressionEl exp2) {
        super(exp1, exp2, 0, false, false, true);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calculateGcd(exp1.evaluate(x, y, z), exp2.evaluate(x, y, z));
    }

    private int calculateGcd(int x, int y) {
        if (x == 0) {
            x = y;
            y = 0;
        }
        while (y != 0) {
            x %= y ;
            int z = y;
            y = x;
            x = z;
        }
        return x > 0 ? x : -x;
    }

    @Override
    public String toString() {
        return super.toString("gcd");
    }

    @Override
    public String toMiniString() {
        return super.toMiniString("gcd");
    }
}
