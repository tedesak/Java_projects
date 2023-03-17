package expression.generic;

import expression.ToMiniString;

import java.util.Objects;

public class GenericVariable<E extends GenericNumber> implements ToMiniString, GenericExpressionEl<E> {
    private String variableName;
    private E baseClass;

    public GenericVariable(String variableName, E baseClass) {
        if(!variableName.equals("x") && !variableName.equals("y") && !variableName.equals("z")) {
            throw new IllegalArgumentException("Illegal variable name:" + variableName);
        }
        this.variableName = variableName;
        this.baseClass = baseClass;
    }

    @Override
    public E evaluate(int x, int y, int z) {
        int ans;
        if (variableName.equals("x")) {
            ans = x;
        } else if (variableName.equals("y")) {
            ans = y;
        } else {
            ans = z;
        }
        return (E) baseClass.init(ans);
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public String toString() {
        return variableName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericVariable<?> that = (GenericVariable<?>) o;
        return variableName.equals(that.variableName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variableName);
    }
}
