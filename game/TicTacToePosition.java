package game;

public class TicTacToePosition implements Position {
    private VariableSizeTicTacToeBoard board;

    TicTacToePosition(VariableSizeTicTacToeBoard board) {
        this.board = board;
    }

    @Override
    public boolean isValid(Move move) {
        return board.isValid(move);
    }

    @Override
    public int getRowQuantity() {
        return board.getRowQuantity();
    }

    @Override
    public int getColumnQuantity() {
        return board.getColumnQuantity();
    }

    @Override
    public int getWinQuantity() {
        return board.getWinQuantity();
    }

    @Override
    public Cell getCell(int r, int c) {
        return board.getCell(r, c);
    }

    public String toString() {
        return board.toString();
    }
}
