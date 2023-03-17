package expression.generic;

public class WithoutOverflowDouble implements GenericNumber {
    private double value;

    @Override
    public GenericNumber init(int val) {
        WithoutOverflowDouble newNumber = new WithoutOverflowDouble();
        newNumber.value = val;
        return newNumber;
    }

    private GenericNumber init(double val) {
        WithoutOverflowDouble newNumber = new WithoutOverflowDouble();
        newNumber.value = val;
        return newNumber;
    }

    @Override
    public GenericNumber add(GenericNumber num) {
        double y = ((WithoutOverflowDouble) num).value;
        return init(value + y);
    }

    @Override
    public GenericNumber subtract(GenericNumber num) {
        double y = ((WithoutOverflowDouble) num).value;
        return init(value - y);
    }

    @Override
    public GenericNumber multiply(GenericNumber num) {
        double y = ((WithoutOverflowDouble) num).value;
        return init(value * y);
    }

    @Override
    public GenericNumber divide(GenericNumber num) {
        double y = ((WithoutOverflowDouble) num).value;
        return init(value / y);
    }

    @Override
    public GenericNumber mode(GenericNumber num) {
        double y = ((WithoutOverflowDouble) num).value;
        return init(value % y);
    }

    @Override
    public GenericNumber abs() {
        return init(Double.compare(value, 0) == -1 ? -value : value);
    }

    @Override
    public GenericNumber unaryMinus() {
        return init(-value);
    }

    @Override
    public GenericNumber square() {
        return init(value * value);
    }

    @Override
    public Number getValue() {
        return Double.valueOf(value);
    }
}
