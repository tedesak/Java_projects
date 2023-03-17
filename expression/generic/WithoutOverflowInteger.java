package expression.generic;

import expression.exceptions.DivisionByZeroException;

public class WithoutOverflowInteger implements GenericNumber {
    private int value;

    @Override
    public GenericNumber init(int val) {
        WithoutOverflowInteger newNumber = new WithoutOverflowInteger();
        newNumber.value = val;
        return newNumber;
    }

    @Override
    public GenericNumber add(GenericNumber num) {
        int y = ((WithoutOverflowInteger) num).value;
        return init(value + y);
    }

    @Override
    public GenericNumber subtract(GenericNumber num) {
        int y = ((WithoutOverflowInteger) num).value;
        return init(value - y);
    }

    @Override
    public GenericNumber multiply(GenericNumber num) {
        int y = ((WithoutOverflowInteger) num).value;
        return init(value * y);
    }

    @Override
    public GenericNumber divide(GenericNumber num) {
        int y = ((WithoutOverflowInteger) num).value;
        if (y == 0) {
            throw new DivisionByZeroException("division by zero");
        }
        return init(value / y);
    }

    @Override
    public GenericNumber mode(GenericNumber num) {
        int y = ((WithoutOverflowInteger) num).value;
        if (y == 0) {
            throw new DivisionByZeroException("division by zero");
        }
        return init(value % y);
    }

    @Override
    public GenericNumber abs() {
        return init(value >= 0 ? value : -value);
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
        return Integer.valueOf(value);
    }
}
