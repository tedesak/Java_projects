package expression.generic;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowException;

public class OverflowInteger implements GenericNumber {
    private int value;

    @Override
    public GenericNumber init(int val) {
        OverflowInteger newNumber = new OverflowInteger();
        newNumber.value = val;
        return newNumber;
    }

    @Override
    public GenericNumber add(GenericNumber num) {
        int y = ((OverflowInteger) num).value;
        if (y > 0 && value > Integer.MAX_VALUE - y || y < 0 && value < Integer.MIN_VALUE - y) {
            throw new OverflowException("overflow");
        }
        return init(value + y);
    }

    @Override
    public GenericNumber subtract(GenericNumber num) {
        int y = ((OverflowInteger) num).value;
        if (y < 0 && value > Integer.MAX_VALUE + y || y > 0 && value < Integer.MIN_VALUE + y) {
            throw new OverflowException("overflow");
        }
        return init(value - y);
    }

    @Override
    public GenericNumber multiply(GenericNumber num) {
        int y = ((OverflowInteger) num).value;
        int ans = value * y;
        if (y != 0 && ans / y != value ||
                value == Integer.MIN_VALUE && y == -1) {
            throw new OverflowException("overflow");
        }
        return init(ans);
    }

    @Override
    public GenericNumber divide(GenericNumber num) {
        int y = ((OverflowInteger) num).value;
        if (y == 0) {
            throw new DivisionByZeroException("division by zero");
        }
        if (value == Integer.MIN_VALUE && y == -1) {
            throw new OverflowException("overflow");
        }
        return init(value / y);
    }

    @Override
    public GenericNumber mode(GenericNumber num) {
        int y = ((OverflowInteger) num).value;
        if (y == 0) {
            throw new DivisionByZeroException("division by zero");
        }
        return init(value % y);
    }

    @Override
    public GenericNumber abs() {
        if (value == Integer.MIN_VALUE) {
            throw new OverflowException("overflow");
        }
        return init(value >= 0 ? value : -value);
    }

    @Override
    public GenericNumber unaryMinus() {
        if (value == Integer.MIN_VALUE) {
            throw new OverflowException("overflow");
        }
        return init(-value);
    }

    @Override
    public GenericNumber square() {
        if (value >= 46341 || value <= -46341) {
            throw new OverflowException("overflow");
        }
        return init(value * value);
    }

    @Override
    public Number getValue() {
        return Integer.valueOf(value);
    }
}
