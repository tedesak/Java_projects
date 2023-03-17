package expression.generic;

import expression.ToMiniString;

import java.util.Objects;

public class GenericConst<E extends GenericNumber> implements ToMiniString, GenericExpressionEl<E> {
    private E value;

    public GenericConst(E value) {
        this.value = value;
    }

    @Override
    public E evaluate(int x, int y, int z) {
        return value;
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericConst<?> that = (GenericConst<?>) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
