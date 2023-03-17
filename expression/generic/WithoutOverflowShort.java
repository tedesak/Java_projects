package expression.generic;

import expression.exceptions.DivisionByZeroException;

public class WithoutOverflowShort implements GenericNumber {
    private short value;

    @Override
    public GenericNumber init(int val) {
        WithoutOverflowShort newNumber = new WithoutOverflowShort();
        newNumber.value = (short) val;
        return newNumber;
    }

    @Override
    public GenericNumber add(GenericNumber num) {
        short y = ((WithoutOverflowShort) num).value;
        return init(value + y);
    }

    @Override
    public GenericNumber subtract(GenericNumber num) {
        short y = ((WithoutOverflowShort) num).value;
        return init(value - y);
    }

    @Override
    public GenericNumber multiply(GenericNumber num) {
        short y = ((WithoutOverflowShort) num).value;
        return init(value * y);
    }

    @Override
    public GenericNumber divide(GenericNumber num) {
        short y = ((WithoutOverflowShort) num).value;
        if (y == 0) {
            throw new DivisionByZeroException("division by zero");
        }
        return init(value / y);
    }

    @Override
    public GenericNumber mode(GenericNumber num) {
        short y = ((WithoutOverflowShort) num).value;
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
        return Short.valueOf(value);
    }
}
