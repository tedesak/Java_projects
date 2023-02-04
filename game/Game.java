package game;

import java.util.List;

public class Game {
    private final boolean log;
    private final List<Player> players;
    private final List<Boolean> isLost;
    private int lostPlayerQuantity;

    public Game(final boolean log, final Player player1, final Player player2) {
        this.log = log;
        players = List.of(player1, player2);
        isLost = List.of(false, false);
        lostPlayerQuantity = 0;
    }

    public Game(final boolean log, final Player player1, final Player player2, final Player player3) {
        this.log = log;
        players = List.of(player1, player2, player3);
        isLost = List.of(false, false, false);
        lostPlayerQuantity = 0;
    }

    public Game(final boolean log, final Player player1, final Player player2, final Player player3, final Player player4) {
        this.log = log;
        players = List.of(player1, player2, player3, player4);
        isLost = List.of(false, false, false, false);
        lostPlayerQuantity = 0;
    }

    public int play(Board board) {
        while (true) {
            int numberPlayer = 0;
            for (Player player:players) {
                if (!isLost.get(numberPlayer)) {
                    final int result = move(board, player, numberPlayer);
                    if (result >= 0) {
                        return result;
                    } else if (result == -2) {
                        isLost.set(numberPlayer, true);
                        lostPlayerQuantity++;
                    }
                }
                if (lostPlayerQuantity == players.size()) {
                    return 0;
                }
                numberPlayer++;
            }
        }
    }

    private int move(final Board board, final Player player, final int no) {
        board.setNextPlayer(no);
        final Move move = player.move(board.getPosition(), board.getCell());
        final Result result = board.makeMove(move);
        log("Player " + (no + 1) + " move: " + move);
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + (no + 1) + " won");
            return no + 1;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else if (result == Result.LOSE) {
            log("Player " + (no + 1) + " lose");
            return -2;
        } else {
            return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
