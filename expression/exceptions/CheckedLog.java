package expression.exceptions;

import expression.CheckedExpressionEl;

public class CheckedLog extends AbstractCheckedUnaryOperation{
    public CheckedLog(CheckedExpressionEl exp) {
        super(exp);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return countLog10(exp.evaluate(x, y, z));
    }

    private int countLog10(int x) {
        if (x <= 0) {
            throw new IllegalArgumentException("negative log\nException expression: " + toMiniString());
        }
        int count = 0;
        for (;x >= 10; x /= 10) {
            count++;
        }
        return count;
    }

    @Override
    public String toString() {
        return super.toString("log10");
    }

    @Override
    public String toMiniString() {
        return super.toMiniString("log10");
    }
}
