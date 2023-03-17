package expression.exceptions;

import expression.parser.*;
import expression.*;

public class Main {
    public static void main(String[] args) throws Exception {
        ExpressionParser parser = new ExpressionParser();
        TripleExpression exp = parser.parse("1000000*x*x*x*x*x/(x-1)");
        System.out.println(exp.evaluate(2,0,0));
    }
}
