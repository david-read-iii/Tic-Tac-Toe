package main;

public class Move {
    int row, column;

    /**
     * Represents a move one may make on a tic-tac-toe board.
     * @param row Vertical coordinate.
     * @param column Horizontal coordinate.
     */
    public Move(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Returns a string representing the move.
     */
    @Override
    public String toString() {
        return "Move{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
