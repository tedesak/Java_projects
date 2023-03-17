package expression.generic;

public interface GenericTripleExpression<E extends GenericNumber> {
    E evaluate(int x, int y, int z);
}
