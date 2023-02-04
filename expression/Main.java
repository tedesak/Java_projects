package expression;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Add(new Subtract( new Multiply(new Variable("x"), new Variable("y"))
                , new Multiply(new Const(2), new Variable("z"))), new Const(1)).evaluate(1, 5, 3));
        System.out.println(new Add(new Subtract( new Multiply(new Variable("x"), new Variable("x"))
                , new Multiply(new Const(2.0), new Variable("x"))), new Const(1)).equals(new Add(new Subtract( new Multiply(new Variable("x"), new Variable("x"))
                , new Multiply(new Const(2.0), new Variable("x"))), new Const(1))));
        System.out.println(new Add(new Variable("x"), new Variable("x")).
                equals(new Add(new Variable("y"), new Variable("x"))));
    }
}
