package game;

public class SequentialPlayer implements Player {
    public SequentialPlayer() {}

    @Override
    public Move move(final Position position, final Cell cell) {
        for (int row = 0; row != position.getRowQuantity(); row++) {
            for (int column = 0; column != position.getColumnQuantity(); column++) {
                final Move move = new Move(row, column, cell);
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }
}
