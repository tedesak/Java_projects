package expression;

import java.util.Objects;

public class Variable implements ExpressionEl, ToMiniString {
    private String variableName;

    public Variable(String variableName) {
        if(!variableName.equals("x") && !variableName.equals("y") && !variableName.equals("z")) {
            throw new IllegalArgumentException("Illegal variable name:" + variableName);
        }
        this.variableName = variableName;
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public double evaluate(double x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if (variableName.equals("x")) {
            return x;
        } else if (variableName.equals("y")) {
            return y;
        } else {
            return z;
        }
    }

    @Override
    public String toString() {
        return variableName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return variableName.equals(variable.variableName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variableName);
    }
}
