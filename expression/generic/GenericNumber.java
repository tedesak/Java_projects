package expression.generic;

public interface GenericNumber {
    GenericNumber init(int val);
    GenericNumber add(GenericNumber num);
    GenericNumber subtract(GenericNumber num);
    GenericNumber multiply(GenericNumber num);
    GenericNumber divide(GenericNumber num);
    GenericNumber mode(GenericNumber num);
    GenericNumber abs();
    GenericNumber unaryMinus();
    GenericNumber square();
    Number getValue();
}
