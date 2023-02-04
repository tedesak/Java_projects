package expression;

import java.util.Objects;

abstract public class AbstractBinOperation implements ToMiniString, ExpressionEl {
    protected final ExpressionEl exp1;
    protected final ExpressionEl exp2;
    protected int operationPriority;
    protected boolean associativeFirst;
    protected boolean associativeSecond;
    protected boolean associativeWithSimilar;

    public AbstractBinOperation(ExpressionEl exp1, ExpressionEl exp2,
                                int operationPriority,
                                boolean associativeFirst, boolean associativeSecond, boolean associativeWithSimilar) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operationPriority = operationPriority;
        this.associativeFirst = associativeFirst;
        this.associativeSecond = associativeSecond;
        this.associativeWithSimilar = associativeWithSimilar;
    }

    protected String toString(String c) {
        return "(" + exp1.toString() + " " + c + " " + exp2.toString() + ")";
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

    protected String toMiniString(String c) {
        String miniString1 = exp1.toMiniString(),
                miniString2 = exp2.toMiniString();
        int operationPriority1 = exp1.getPriority(),
                operationPriority2 = exp2.getPriority();
        if(operationPriority > operationPriority1) {
            miniString1 = "(" + miniString1 + ")";
        }
        if (associativeWithSimilar && getClass() == exp2.getClass()) {
            return miniString1 + " " + c + " " + miniString2;
        }
        if((operationPriority2 < operationPriority) ||
                (operationPriority2 == operationPriority) &&
                (!getAssociativeSecond() ||
                        exp2 instanceof AbstractBinOperation &&
                                !((AbstractBinOperation) exp2).getAssociativeFirst())) {
            miniString2 = "(" + miniString2 + ")";
        }
        return miniString1 + " " + c + " " + miniString2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractBinOperation that = (AbstractBinOperation) o;
        return operationPriority == that.operationPriority && associativeFirst == that.associativeFirst &&
                associativeSecond == that.associativeSecond && associativeWithSimilar == that.associativeWithSimilar &&
                exp1.equals(that.exp1) && exp2.equals(that.exp2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exp1, exp2, operationPriority, associativeFirst, associativeSecond, associativeWithSimilar);
    }
}
