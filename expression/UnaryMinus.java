package expression;

public class UnaryMinus extends AbstractUnaryOperation {
    public UnaryMinus(ExpressionEl exp) {
        super(exp);
    }

    @Override
    public double evaluate(double x) {
        throw new IllegalArgumentException();
    }

    @Override
    public int evaluate(int x) {
        return -exp.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
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
