package expression.generic;

import expression.exceptions.DivisionByZeroException;

public class ModularInteger implements GenericNumber {
    private final int MODULE = 10079;
    private int value;

    @Override
    public GenericNumber init(int val) {
        ModularInteger newNumber = new ModularInteger();
        newNumber.value = mode(val);
        return newNumber;
    }

    @Override
    public GenericNumber add(GenericNumber num) {
        int y = ((ModularInteger) num).value;
        return init(mode(value + y));
    }

    @Override
    public GenericNumber subtract(GenericNumber num) {
        int y = ((ModularInteger) num).value;
        return init(mode(value - y));
    }

    @Override
    public GenericNumber multiply(GenericNumber num) {
        int y = ((ModularInteger) num).value;
        return init(mode(value * y));
    }

    @Override
    public GenericNumber divide(GenericNumber num) {
        int y = mode(((ModularInteger) num).value);
        if (y == 0) {
            throw new DivisionByZeroException("division by zero");
        }
        return init(mode(value * fastPow(y, MODULE - 2, MODULE)));
    }

    private int fastPow(int x, int n, int m) {
        int ans = 1;
        for (int i = n; i != 0; i /= 2) {
            if (i % 2 == 1) {
                ans = (ans * x) % m;
            }
            x = x * x % m;
        }
        return ans;
    }

    @Override
    public GenericNumber mode(GenericNumber num) {
        int y = mode(((ModularInteger) num).value);
        if (y == 0) {
            throw new DivisionByZeroException("division by zero");
        }
        return init(mode(value % y));
    }

    @Override
    public GenericNumber abs() {
        return init(mode(value >= 0 ? value : -value));

    }

    @Override
    public GenericNumber unaryMinus() {
        return init(mode(-value));
    }

    @Override
    public GenericNumber square() {
        return init(mode(value * value));
    }

    @Override
    public Number getValue() {
        return Integer.valueOf(value);
    }

    private int mode(int x) {
        return (x % MODULE + MODULE) % MODULE;
    }
}
