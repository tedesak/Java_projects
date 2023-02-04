package expression.parser;

import expression.*;

public class ExpressionParser implements TripleParser {
    @Override
    public TripleExpression parse(final String expression) {
        return parse(new StringSource(expression));
    }

    public TripleExpression parse(final CharSource expression) {
        return new Parser(expression).parse();
    }

    private static class Parser extends BaseParser {
        protected Parser(final CharSource source) {
            super(source);
        }

        public ExpressionEl parse() {
            return parsePriority0();
        }

        private ExpressionEl parsePriority0() {
            ExpressionEl actual = parsePriority1();
            while (true) {
                skipWhitespace();
                if (take('g')) {
                    expect("cd");
                    actual = new Gcd(actual, parsePriority1());
                } else if (take('l')) {
                    expect("cm");
                    actual = new Lcm(actual, parsePriority1());
                } else {
                    return actual;
                }
            }
        }

        private ExpressionEl parsePriority1() {
            ExpressionEl actual = parsePriority2();
            skipWhitespace();
            while (!eof()) {
                if (take('+')) {
                    actual = new Add(actual, parsePriority2());
                } else if (take('-')) {
                    actual = new Subtract(actual, parsePriority2());
                } else {
                    break;
                }
                skipWhitespace();
            }
            return actual;
        }

        private ExpressionEl parsePriority2() {
            ExpressionEl actual = parseUnary();
            skipWhitespace();
            while (!eof()) {
                if (take('*')) {
                    actual = new Multiply(actual, parseUnary());
                } else if (take('/')) {
                    actual = new Divide(actual, parseUnary());
                } else {
                    break;
                }
                skipWhitespace();
            }
            return actual;
        }

        private ExpressionEl parseUnary() {
            ExpressionEl actual;
            skipWhitespace();
            actual = reparseUnary();
            skipWhitespace();
            return actual;
        }

        private ExpressionEl reparseUnary() {
            if (take('-')) {
                if (between('0', '9')) {
                    return parseConst(true);
                } else {
                    return  new UnaryMinus(parseUnary());
                }
            } else if (take('r')) {
                expect("everse");
                return new Reverse(parseUnary());
            } else if (take('(')) {
                ExpressionEl actual = parse();
                skipWhitespace();
                take(')');
                return actual;
            } else {
                return parseNumber();
            }
        }

        private ExpressionEl parseNumber() {
            ExpressionEl actual;
            skipWhitespace();
            if (between('0', '9') || test() == '-') {
                actual = parseConst(false);
            } else {
                actual = parseVariable();
            }
            return actual;
        }

        private ExpressionEl parseVariable() {
            ExpressionEl actual;
            skipWhitespace();
            if (take('x')) {
                actual = new Variable("x");
            } else if (take('y')) {
                actual = new Variable("y");
            } else if (take('z')) {
                actual = new Variable("z");
            } else {
                throw new IllegalArgumentException("Illegal variable name");
            }
            return actual;
        }

        private ExpressionEl parseConst(boolean wasMinus) {
            final StringBuilder sb = new StringBuilder();
            if (wasMinus) {
                sb.append('-');
            }
            takeInteger(sb);
            try {
                final int val = Integer.parseInt(sb.toString());
                return new Const(val);
            } catch (final NumberFormatException e) {
                throw error("Invalid number " + sb);
            }
        }

        private void takeInteger(final StringBuilder sb) {
            if (take('-')) {
                sb.append('-');
            }
            if (take('0')) {
                sb.append(0);
                return;
            }
            while (between('0', '9')) {
                sb.append(take());
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

    public static void main(final String... args) {
        System.out.println(new ExpressionParser().parse("x gcdx"));
    }
}
