package expression.parser;

import expression.ExpressionEl;
import expression.TripleExpression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionParserTest {
    private void valid(int expected, String input, int xValue, int yValue, int zValue) {
        ExpressionParser parser = new ExpressionParser();
        TripleExpression expression = parser.parse(input);
        System.out.println(expression.toMiniString());
        System.out.println(expression.toString());
        Assertions.assertEquals(expected, expression.evaluate(xValue, yValue, zValue));
    }

    @Test
    public void testElementaryExpression() {
        valid(-1, "\u000B\u000B\t-1", 100, 0, 0);
        valid(-100, "-x", 100, 0, 0);
        valid(100, "x", 100, 0, 0);
        valid(980001,"x * (x - 2)*x + 1", 100, 0, 0);
        valid(90000,"x * (x - 2)*x + (-1) * (-x) * (-x) * 89", 100, 0, 0);
    }
    @Test
    public void testWhitespace() {
        valid(90000,"                 x *       (     x - 2    )*x + (     -1       )" +
                " * (    -x    ) *    (   -x    ) *    " +
                "89      ", 100, 0, 0);
        valid(-100, "-                 x", 100, 0, 0);
    }

    @Test
    void testGcdLcm() {
        //valid(0, "(0 gcd 0)", 0, 0, 0);
        //valid(0, " ", 0, 0, 0);
        valid(1, "x gcdx", 1, 1, 1);
        valid(-2147483648, "(4 lcm\t\t\f\u000B-2147483648)\u2029", 0, 0, 0);
    }

    @Test
    public void testToMiniString() {
        valid(0,"((2+2))-0/(--2)*555",0,1,0);
    }
}