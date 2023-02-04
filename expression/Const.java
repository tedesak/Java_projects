package expression;

import java.util.Objects;

public class Const implements ExpressionEl, ToMiniString {
    private double value;
    private boolean isItInt;

    public Const(int intValue) {
        value = intValue;
        isItInt = true;
    }

    public Const(double doubleValue) {
        value = doubleValue;
        isItInt = false;
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int evaluate(int x) {
        return (int) value;
    }

    @Override
    public double evaluate(double x) {
        return value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (int) value;
    }

    @Override
    public String toString() {
        if (isItInt) {
            return Integer.toString((int) value);
        } else {
            return Double.toString(value);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Const aConst = (Const) o;
        return Double.compare(aConst.value, value) == 0 && isItInt == aConst.isItInt;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, isItInt);
    }
}
