package expression.generic;

import expression.exceptions.*;
import expression.parser.BaseParser;
import expression.parser.CharSource;
import expression.parser.StringSource;

import java.util.Set;

public class ExpressionParser<E extends GenericNumber> {
    public GenericTripleExpression<E> parse(final String expression, final E baseClass) throws IncorrectExpressionException {
        return parse(new StringSource(expression), baseClass);
    }

    public GenericTripleExpression<E> parse(final CharSource expression, final E baseClass) throws IncorrectExpressionException {
        return new Parser(expression, baseClass).parse();
    }

    private static class Parser<E extends GenericNumber> extends BaseParser {
        final Set<Character> BASE_OPERATIONS = Set.of('+', '-', '*', '/');
        final E baseClass;

        private String opName;

        protected Parser(final CharSource source, final E baseClass) {
            super(source);
            this.baseClass = baseClass;
            opName = "";
        }

        public GenericExpressionEl<E> parse() throws IncorrectExpressionException {
            GenericExpressionEl<E> ans = parseAddSubtract();
            skipWhitespace();
            if (!eof() || !opName.isEmpty()) {
                throw new IncorrectExpressionException("expected: end of expression\nfound: continuation of expression");
            }
            return ans;
        }

        private GenericExpressionEl<E> parseAddSubtract() throws IncorrectExpressionException {
            GenericExpressionEl<E> actual = parseMultiplyDivideMode();
            while (!eof()) {
                getNextOp();
                if (nameEquals("+")) {
                    actual = new GenericAdd<>(actual, parseMultiplyDivideMode());
                } else if (nameEquals("-")) {
                    actual = new GenericSubtract<>(actual, parseMultiplyDivideMode());
                } else {
                    break;
                }
            }
            return actual;
        }

        private GenericExpressionEl<E> parseMultiplyDivideMode() throws IncorrectExpressionException {
            GenericExpressionEl<E> actual = parseUnary();
            while (!eof()) {
                getNextOp();
                if (nameEquals("*")) {
                    actual = new GenericMultiply<>(actual, parseUnary());
                } else if (nameEquals("/")) {
                    actual = new GenericDivide<>(actual, parseUnary());
                } else if (nameEquals("mod")) {
                    actual = new GenericMode<>(actual, parseUnary());
                } else {
                    break;
                }
            }
            return actual;
        }

        private GenericExpressionEl<E> parseUnary() throws IncorrectExpressionException {
            getNextOp();
            if (nameEquals("-")) {
                if (between('0', '9')) {
                    getNextOp();
                    return parseConst(true);
                } else {
                    return new GenericUnaryMinus<E>(parseUnary());
                }
            } else if (nameEquals("abs")) {
                return new GenericAbs<>(parseUnary());
            } else if (nameEquals("square")) {
                return new GenericSquare<>(parseUnary());
            } else if (opName.isEmpty() && take('(')) {
                GenericExpressionEl<E> actual = parseAddSubtract();
                skipWhitespace();
                if (!take(')')) {
                    throw new IncorrectExpressionException("not found ')'");
                }
                return actual;
            } else if (opName.isEmpty()) {
                throw new NoArgumentException("no argument");
            } else {
                return parseNumber();
            }
        }

        private GenericExpressionEl<E> parseNumber() throws IncorrectExpressionException {
            if (opName.charAt(0) >= '0' && opName.charAt(0) <= '9' || opName.charAt(0) == '-') {
                return parseConst(false);
            } else {
                return parseVariable();
            }
        }

        private GenericExpressionEl<E> parseVariable() throws IncorrectExpressionException {
            if (nameEquals("x")) {
                return new GenericVariable<>("x", baseClass);
            } else if (nameEquals("y")) {
                return new GenericVariable<>("y", baseClass);
            } else if (nameEquals("z")) {
                return new GenericVariable<>("z", baseClass);
            } else {
                throw new NoArgumentException("incorrect argument: " + opName);
            }
        }

        private GenericExpressionEl<E> parseConst(boolean wasMinus) throws ConstFormatException {
            final StringBuilder sb = new StringBuilder();
            if (wasMinus) {
                sb.append('-');
            }
            sb.append(opName);
            opName = "";
            final String number = sb.toString();
            try {
                int val = Integer.parseInt(number);
                return new GenericConst<>((E) baseClass.init(val));
            } catch (final NumberFormatException e) {
                throw new ConstFormatException("incorrect const: " + number);
            }
        }

        private void getNextOp() {
            if (opName.isEmpty()) {
                skipWhitespace();
                if (BASE_OPERATIONS.contains(test())) {
                    opName = Character.toString(take());
                    return;
                }
                StringBuilder opNameBuilder = new StringBuilder();
                while (!(BASE_OPERATIONS.contains(test()))
                        && test() != '(' && test() != ')' &&
                        !Character.isWhitespace(test()) && !eof()
                ) {
                    opNameBuilder.append(take());
                }
                opName = opNameBuilder.toString();
            }
        }

        private boolean nameEquals(String expected) {
            if (opName.equals(expected)) {
                opName = "";
                return true;
            } else {
                return false;
            }
        }

        private boolean skipWhitespace() {
            boolean wasSkip = false;
            while (Character.isWhitespace(test())) {
                take();
                wasSkip = true;
            }
            return wasSkip;
        }
    }
}
