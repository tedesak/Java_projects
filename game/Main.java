package game;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int result, stringQuantity, columnQuantity, winQuantity;
        final Game game = new Game(true, new RandomPlayer(), new RandomPlayer(), new HumanPlayer(System.out, scanner));
        //final Game game = new Game(true, new HumanPlayer(System.out, scanner), new RandomPlayer());
        //final Game game = new Game(true, new RandomPlayer(), new RandomPlayer());
        //final Game game = new Game(true, new SequentialPlayer(), new SequentialPlayer());
        //final Game game = new Game(false, new RandomPlayer(), new RandomPlayer(), new RandomPlayer(), new RandomPlayer());
        //final Game game = new Game(true, new SequentialPlayer(), new BadPlayer());

        //final Tournament tournament = new Tournament(false, List.of(new HumanPlayer(System.out, scanner), new RandomPlayer(), new RandomPlayer()), System.out);
        //final Tournament tournament = new Tournament(false, List.of(new RandomPlayer(), new RandomPlayer(), new RandomPlayer(), new RandomPlayer(), new RandomPlayer(), new RandomPlayer(), new RandomPlayer(), new RandomPlayer(), new RandomPlayer()), System.out);


        stringQuantity = scanner.nextInt();
        columnQuantity = scanner.nextInt();
        winQuantity = scanner.nextInt();
        //int[] letColumn = null;
        //int[] letRow = null;


        //int[] letColumn = new int[]{0,0,0,0,0,0,0,0,0,0,0,1,2,3,4,5,6,7,8,9,10,10,10,10,10,10,10,10,10,10,10,9,8,7,6,5,4,3,2,1};
        //int[] letRow = new int[]   {0,1,2,3,4,5,6,7,8,9,10,10,10,10,10,10,10,10,10,10,10,9,8,7,6,5,4,3,2,1,0,0,0,0,0,0,0,0,0,0};
        //int[] letColumn = new int[]{0,1,2,3,4,5,6,7,8,9,10,0,1,2,3,4,6,7,8,9,10};
        //int[] letRow = new int[]   {0,1,2,3,4,5,6,7,8,9,10,10,9,8,7,6,4,3,2,1,0};
        //tournament.play(new VariableSizeTicTacToeBoard(11, 11, 4, letRow, letColumn));


        //result = game.play(new VariableSizeTicTacToeBoard(11, 11, 4, letRow, letColumn));
        //System.out.println("Game result: " + result);

        //tournament.play(new VariableSizeTicTacToeBoard(stringQuantity, columnQuantity, winQuantity));
        result = game.play(new VariableSizeTicTacToeBoard(stringQuantity, columnQuantity, winQuantity));

    }
}
