package expression.exceptions;

import expression.*;
import expression.parser.BaseParser;
import expression.parser.StringSource;
import expression.parser.CharSource;

public class ExpressionParser implements TripleParser {
    @Override
    public TripleExpression parse(final String expression) throws IncorrectExpressionException {
        return parse(new StringSource(expression));
    }

    public TripleExpression parse(final CharSource expression) throws IncorrectExpressionException {
        return new Parser(expression).parse();
    }

    private static class Parser extends BaseParser {
        private String opName;

        protected Parser(final CharSource source) {
            super(source);
            opName = "";
        }

        public CheckedExpressionEl parse() throws IncorrectExpressionException {
            CheckedExpressionEl ans = parsePriority0();
            skipWhitespace();
            if (!eof() || !opName.isEmpty()) {
                throw new IncorrectExpressionException("expected: end of expression\nfound: continuation of expression");
            }
            return ans;
        }

        private CheckedExpressionEl parsePriority0() throws IncorrectExpressionException {
            CheckedExpressionEl actual = parsePriority1();
            while (true) {
                getNextOp();
                if (nameEquals("gcd")) {
                    actual = new CheckedGcd(actual, parsePriority1());
                } else if (nameEquals("lcm")) {
                    actual = new CheckedLcm(actual, parsePriority1());
                } else {
                    return actual;
                }
            }
        }

        private CheckedExpressionEl parsePriority1() throws IncorrectExpressionException {
            CheckedExpressionEl actual = parsePriority2();
            while (!eof()) {
                getNextOp();
                if (nameEquals("+")) {
                    actual = new CheckedAdd(actual, parsePriority2());
                } else if (nameEquals("-")) {
                    actual = new CheckedSubtract(actual, parsePriority2());
                } else {
                    break;
                }
            }
            return actual;
        }

        private CheckedExpressionEl parsePriority2() throws IncorrectExpressionException {
            CheckedExpressionEl actual = parseUnary();
            while (!eof()) {
                getNextOp();
                if (nameEquals("*")) {
                    actual = new CheckedMultiply(actual, parseUnary());
                } else if (nameEquals("/")) {
                    actual = new CheckedDivide(actual, parseUnary());
                } else {
                    break;
                }
            }
            return actual;
        }

        private CheckedExpressionEl parseUnary() throws IncorrectExpressionException {
            getNextOp();
            if (nameEquals("-")) {
                if (between('0', '9')) {
                    getNextOp();
                    return parseConst(true);
                } else {
                    return new CheckedNegate(parseUnary());
                }
            } else if (opName.isEmpty() && take('(')) {
                CheckedExpressionEl actual = parsePriority0();
                skipWhitespace();
                if (!take(')')) {
                    throw new IncorrectExpressionException("not found ')'");
                }
                return actual;
            } else if (nameEquals("reverse")) {
                return new CheckedReverse(parseUnary());
            } else if (nameEquals("log10")) {
                return new CheckedLog(parseUnary());
            } else if (nameEquals("pow10")) {
                return new CheckedPow(parseUnary());
            } else if (opName.isEmpty()) {
                throw new NoArgumentException("no argument");
            } else {
                return parseNumber();
            }
        }

        private CheckedExpressionEl parseNumber() throws IncorrectExpressionException {
            if (opName.charAt(0) >= '0' && opName.charAt(0) <= '9' || opName.charAt(0) == '-') {
                return parseConst(false);
            } else {
                return parseVariable();
            }
        }

        private CheckedExpressionEl parseVariable() throws IncorrectExpressionException {
            if (nameEquals("x")) {
                return new Variable("x");
            } else if (nameEquals("y")) {
                return new Variable("y");
            } else if (nameEquals("z")) {
                return new Variable("z");
            } else {
                throw new NoArgumentException("incorrect argument: " + opName);
            }
        }

        private CheckedExpressionEl parseConst(boolean wasMinus) throws ConstFormatException {
            final StringBuilder sb = new StringBuilder();
            if (wasMinus) {
                sb.append('-');
            }
            sb.append(opName);
            opName = "";
            final String number = sb.toString();
            try {
                final int val = Integer.parseInt(number);
                return new Const(val);
            } catch (final NumberFormatException e) {
                throw new ConstFormatException("incorrect const: " + number);
            }
        }

        private void getNextOp() {
            if (opName.isEmpty()) {
                skipWhitespace();
                if (test() == '+' || test() == '-' || test() == '/' || test() == '*') {
                    opName = Character.toString(take());
                    return;
                }
                StringBuilder opNameBuilder = new StringBuilder();
//                for (; test() != '(' && test() != ')' &&
//                        test() != '+' && test() != '-' &&
//                        test() != '/' && test() != '*' &&
//                        !Character.isWhitespace(test()) && !eof();
//                     opNameBuilder.append(take()));
                while (test() != '(' && test() != ')' &&
                       test() != '+' && test() != '-' &&
                       test() != '/' && test() != '*' &&
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
