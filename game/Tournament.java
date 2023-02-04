package game;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Tournament {
    private final boolean log;
    private final List<Player> players;
    private final int[] score;
    private final PrintStream out;

    public Tournament(final boolean log, final List<Player> players, final PrintStream out) {
        this.out = out;
        this.players = new ArrayList<Player>(players);
        score = new int[players.size()];
        this.log = log;
    }

    public void play(Board board) {
        out.println("Points Table:\nPlayer - Point");
        for (int index1 = 0; index1 != players.size(); index1++) {
            for (int index2 = 0; index2 != players.size(); index2++) {
                if (index1 == index2) {
                    continue;
                }
                final Game game = new Game(log, players.get(index1), players.get(index2));
                final int result = game.play(board);
                board.clear();
                if (result == 1) {
                    score[index1] += 3;
                } else if(result == 2) {
                    score[index2] += 3;
                } else {
                    score[index1]++;
                    score[index2]++;
                }
            }
        }
        for (int index = 0; index != players.size(); index++) {
            out.println((index + 1) + " - " + score[index]);
        }
    }
}
