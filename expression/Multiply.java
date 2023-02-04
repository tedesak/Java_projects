package expression;

public class Multiply extends AbstractBinOperation {
    public Multiply(ExpressionEl exp1, ExpressionEl exp2) {
        super(exp1, exp2, 2, true, true, false);
    }

    @Override
    public int evaluate(int x) {
        return exp1.evaluate(x) * exp2.evaluate(x);
    }

    @Override
    public double evaluate(double x) {
        return exp1.evaluate(x) * exp2.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return exp1.evaluate(x, y, z) * exp2.evaluate(x, y, z);
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
