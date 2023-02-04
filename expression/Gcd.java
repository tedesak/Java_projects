package expression;

public class Gcd extends AbstractBinOperation {
    public Gcd(ExpressionEl exp1, ExpressionEl exp2) {
        super(exp1, exp2, 0, false, false, true);
    }

    @Override
    public int evaluate(int x) {
        return calculateGcd(exp1.evaluate(x), exp2.evaluate(x));
    }

    @Override
    public double evaluate(double x) {
        throw new IllegalArgumentException();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calculateGcd(exp1.evaluate(x, y, z), exp2.evaluate(x, y, z));
    }

    private int calculateGcd(int x, int y) {
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
