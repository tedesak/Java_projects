package game;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            Move move;
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            in.nextLine();
            while (true) {
                if (!in.hasNextInt()) {
                    in.nextLine();
                    out.println("Invalid symbols.\nPlease, enter row and column again");
                    continue;
                }
                move = new Move(in.nextInt() - 1, in.nextInt() - 1, cell);
                if (position.isValid(move)) {
                    return move;
                } else {
                    out.println("Move " + move + " is invalid.\nPlease, enter row and column again");
                }
            }
        }
    }
}
