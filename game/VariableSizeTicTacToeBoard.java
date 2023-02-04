package game;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class VariableSizeTicTacToeBoard implements Board,Position {
    private final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.Y, '-',
            Cell.Z, '|',
            Cell.E, '.',
            Cell.BAN, '~'
    );
    private final List<Cell> SYMBOLS_NUMERATION = List.of(
            Cell.X, Cell.O, Cell.Y, Cell.Z
    );
    private final int[] letRow;
    private final int[] letColumn;
    private final Cell[][] cells;
    private int numberEmpty;
    private final int rowQuantity;
    private final int columnQuantity;
    private final int winQuantity;
    private Cell turn;

    public VariableSizeTicTacToeBoard(int rowQuantity, int columnQuantity, int winQuantity, int[] letRow, int[] letColumn) {
        this.rowQuantity = rowQuantity;
        this.columnQuantity = columnQuantity;
        this.winQuantity = winQuantity;
        this.cells = new Cell[rowQuantity][columnQuantity];
        numberEmpty = rowQuantity * columnQuantity - letRow.length;
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        if (letRow.length != letColumn.length) {
            throw new IllegalArgumentException("letRow and letColumn have different size");
        }
        this.letRow = letRow.clone();
        this.letColumn = letColumn.clone();
        for (int index = 0; index != letRow.length; index++) {
            cells[letRow[index]][letColumn[index]] = Cell.BAN;
        }
        this.turn = Cell.X;
    }

    public VariableSizeTicTacToeBoard(int rowQuantity, int columnQuantity, int winQuantity) {
        this.rowQuantity = rowQuantity;
        this.columnQuantity = columnQuantity;
        this.winQuantity = winQuantity;
        this.cells = new Cell[rowQuantity][columnQuantity];
        numberEmpty = rowQuantity * columnQuantity;
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        letRow = null;
        letColumn = null;
        this.turn = Cell.X;
    }

    @Override
    public void clear() {
        numberEmpty = rowQuantity * columnQuantity;
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        this.turn = Cell.X;
        if (letRow != null) {
            numberEmpty -= letRow.length;
            for (int index = 0; index != letRow.length; index++) {
                cells[letRow[index]][letColumn[index]] = Cell.BAN;
            }
        }
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Position getPosition() {
        return new TicTacToePosition(this);
    }

    @Override
    public int getRowQuantity() {
        return rowQuantity;
    }

    @Override
    public int getColumnQuantity() {
        return columnQuantity;
    }

    @Override
    public int getWinQuantity() {
        return winQuantity;
    }

    public void setNextPlayer(final int numberPlayer) {
        turn = SYMBOLS_NUMERATION.get(numberPlayer);
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        cells[move.getRow()][move.getColumn()] = move.getValue();
        numberEmpty--;
        int inDiag1 = 0;
        int inDiag2 = 0;
        int inRow = 0;
        int inColumn = 0;
        for (int delta = 1 - winQuantity; delta != winQuantity; delta++) {
            if (delta + move.getRow() >= 0 && delta + move.getRow() < rowQuantity && cells[delta + move.getRow()][move.getColumn()] == turn) {
                inRow++;
            } else {
                inRow = 0;
            }
            if (delta + move.getColumn() >= 0 && delta + move.getColumn() < columnQuantity && cells[move.getRow()][delta + move.getColumn()] == turn) {
                inColumn++;
            } else {
                inColumn = 0;
            }
            if (delta + move.getRow() >= 0 && delta + move.getRow() < rowQuantity &&
                    delta + move.getColumn() >= 0 && delta + move.getColumn() < columnQuantity &&
                    cells[delta + move.getRow()][delta + move.getColumn()] == turn) {
                inDiag1++;
            } else {
                inDiag1 = 0;
            }
            if (delta + move.getRow() >= 0 && delta + move.getRow() < rowQuantity &&
                    move.getColumn() - delta >= 0 && move.getColumn() - delta < columnQuantity &&
                    cells[delta + move.getRow()][move.getColumn() - delta] == turn) {
                inDiag2++;
            } else {
                inDiag2 = 0;
            }
            if (inRow == winQuantity || inColumn == winQuantity || inDiag1 == winQuantity || inDiag2 == winQuantity) {
                return Result.WIN;
            }
        }
        if (numberEmpty == 0) {
            return Result.DRAW;
        }
        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < rowQuantity
                && 0 <= move.getColumn() && move.getColumn() < columnQuantity
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == move.getValue();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int r = 0; r < rowQuantity; r++) {
            for (int c = 0; c < columnQuantity; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
