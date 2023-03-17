package expression.generic;

import expression.exceptions.IllegalArgumentException;

public class GenericTabulator implements Tabulator {
    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        if (mode.equals("i")) {
            return new Calculator<OverflowInteger>().calculate(new OverflowInteger(), expression, x1, x2, y1, y2, z1, z2);
        } else if ( mode.equals("u")) {
            return new Calculator<WithoutOverflowInteger>().calculate(new WithoutOverflowInteger(), expression, x1, x2, y1, y2, z1, z2);
        } else if (mode.equals("p")) {
            return new Calculator<ModularInteger>().calculate(new ModularInteger(), expression, x1, x2, y1, y2, z1, z2);
        } else if (mode.equals("d")) {
            return new Calculator<WithoutOverflowDouble>().calculate(new WithoutOverflowDouble(), expression, x1, x2, y1, y2, z1, z2);
        } else if (mode.equals("bi")) {
            return new Calculator<GenericBigInteger>().calculate(new GenericBigInteger(), expression, x1, x2, y1, y2, z1, z2);
        } else if (mode.equals("s")) {
            return new Calculator<WithoutOverflowShort>().calculate(new WithoutOverflowShort(), expression, x1, x2, y1, y2, z1, z2);
        } else {
            throw new RuntimeException("incorrect expression mode:" + mode);
        }
    }

    private static class Calculator<E extends GenericNumber> {
        private Object[][][] calculate(final E baseClass, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
            ExpressionParser<E> parser = new ExpressionParser<>();
            GenericTripleExpression<E> genericExpression = parser.parse(expression, baseClass);
            Object[][][] result = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
            for (int i = 0; i <= x2 - x1; i++) {
                for (int j = 0 ; j <= y2 - y1; j++) {
                    for (int k = 0 ;k <= z2 - z1; k++) {
                        Object obj;
                        try {
                            obj = genericExpression.evaluate(x1 + i, y1 + j, z1 + k).getValue();
                        } catch (IllegalArgumentException e) {
                            obj = null;
                        }
                        result[i][j][k] = obj;
                    }
                }
            }
            return result;
        }
    }
}
