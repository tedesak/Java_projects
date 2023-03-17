package expression.generic;

import java.util.Objects;

public abstract class AbstractGenericBinOperation<E extends GenericNumber> implements GenericExpressionEl<E> {
    protected final GenericExpressionEl<E> exp1;
    protected final GenericExpressionEl<E> exp2;
    protected int operationPriority;
    protected boolean associativeFirst;
    protected boolean associativeSecond;
    protected boolean associativeWithSimilar;

    public AbstractGenericBinOperation(GenericExpressionEl<E> exp1, GenericExpressionEl<E> exp2,
                                       int operationPriority, boolean associativeFirst,
                                       boolean associativeSecond, boolean associativeWithSimilar) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operationPriority = operationPriority;
        this.associativeFirst = associativeFirst;
        this.associativeSecond = associativeSecond;
        this.associativeWithSimilar = associativeWithSimilar;
    }

    @Override
    public int getPriority() {
        return operationPriority;
    }

    private boolean getAssociativeFirst() {
        return associativeFirst;
    }

    private boolean getAssociativeSecond() {
        return associativeSecond;
    }

    @Override
    public E evaluate(int x, int y, int z) {
        E ev1 = exp1.evaluate(x, y, z);
        E ev2 = exp2.evaluate(x, y, z);
        return evaluateImpl(ev1, ev2);
    }

    protected abstract E evaluateImpl(E ev1, E ev2);

    @Override
    public String toString() {
        return "(" + exp1.toString() + " " + opString() + " " + exp2.toString() + ")";
    }

    @Override
    public String toMiniString() {
        String opName = opString();
        String miniString1 = exp1.toMiniString(),
                miniString2 = exp2.toMiniString();
        int operationPriority1 = exp1.getPriority(),
                operationPriority2 = exp2.getPriority();
        if(operationPriority > operationPriority1) {
            miniString1 = "(" + miniString1 + ")";
        }
        if (associativeWithSimilar && getClass() == exp2.getClass()) {
            return miniString1 + " " + opName + " " + miniString2;
        }
        if((operationPriority2 < operationPriority) ||
                (operationPriority2 == operationPriority) &&
                        (!getAssociativeSecond() ||
                                exp2 instanceof AbstractGenericBinOperation<?> &&
                                        !((AbstractGenericBinOperation) exp2).getAssociativeFirst())) {
            miniString2 = "(" + miniString2 + ")";
        }
        return miniString1 + " " + opName + " " + miniString2;
    }

    protected abstract String opString();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractGenericBinOperation<?> that = (AbstractGenericBinOperation<?>) o;
        return operationPriority == that.operationPriority && associativeFirst == that.associativeFirst && associativeSecond == that.associativeSecond && associativeWithSimilar == that.associativeWithSimilar && exp1.equals(that.exp1) && exp2.equals(that.exp2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exp1, exp2, operationPriority, associativeFirst, associativeSecond, associativeWithSimilar);
    }
}
