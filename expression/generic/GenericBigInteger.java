package expression.generic;

import expression.exceptions.DivisionByZeroException;

import java.math.BigInteger;

public class GenericBigInteger implements GenericNumber {
    private BigInteger value;

    @Override
    public GenericNumber init(int val) {
        GenericBigInteger newNumber = new GenericBigInteger();
        newNumber.value = BigInteger.valueOf(val);
        return newNumber;
    }

    private GenericNumber init(BigInteger val) {
        GenericBigInteger newNumber = new GenericBigInteger();
        newNumber.value = val;
        return newNumber;
    }

    @Override
    public GenericNumber add(GenericNumber num) {
        return init(value.add(((GenericBigInteger) num).value));
    }

    @Override
    public GenericNumber subtract(GenericNumber num) {
        return init(value.subtract(((GenericBigInteger) num).value));
    }

    @Override
    public GenericNumber multiply(GenericNumber num) {
        return init(value.multiply(((GenericBigInteger) num).value));
    }

    @Override
    public GenericNumber divide(GenericNumber num) {
        BigInteger y = ((GenericBigInteger) num).value;
        if (y.compareTo(BigInteger.ZERO) == 0) {
            throw new DivisionByZeroException("division by zero");
        }
        return init(value.divide(y));
    }

    @Override
    public GenericNumber mode(GenericNumber num) {
        BigInteger y = ((GenericBigInteger) num).value;
        if (y.compareTo(BigInteger.ZERO) <= 0) {
            throw new DivisionByZeroException("division by zero");
        }
        return init(value.mod(y));
    }

    @Override
    public GenericNumber abs() {
        return init(value.abs());
    }

    @Override
    public GenericNumber unaryMinus() {
        return init(value.negate());
    }

    @Override
    public GenericNumber square() {
        return init(value.multiply(value));
    }

    @Override
    public Number getValue() {
        return value;
    }
}
