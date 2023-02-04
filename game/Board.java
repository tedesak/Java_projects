package game;

public interface Board {
    Position getPosition();

    void clear();

    Cell getCell();

    void setNextPlayer(final int numberPlayer);

    Result makeMove(Move move);
}
