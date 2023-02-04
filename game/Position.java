package game;

public interface Position {
    boolean isValid(Move move);

    int getRowQuantity();

    int getColumnQuantity();

    int getWinQuantity();

    Cell getCell(int r, int c);
}
