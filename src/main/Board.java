package main;

public class Board {
    char[][] board;
    char player1, player2;

    /**
     * Represents a tic-tac-toe board.
     * @param player1 The character that represents player 1.
     * @param player2 The character that represents player 2.
     */
    public Board(char player1, char player2) {
        this.board = new char[3][3];
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Has a given player take an action on the board given coordinates.
     * @param move The coordinates.
     * @param player The player's number.
     */
    public void makeMove(Move move, int player) {
        if(player == 1) {
            board[move.getRow()][move.getColumn()] = player1;
        } else if (player == 2) {
            board[move.getRow()][move.getColumn()] = player2;
        }
    }

    /**
     * Undoes the action a player takes at the given coordinates.
     * @param move The coordinates.
     */
    public void undoMove(Move move) {
        board[move.getRow()][move.getColumn()] = 0;
    }

    public char[][] getBoard() {
        return board;
    }

    /**
     * Returns a string representing the state of the tic-tac-toe board.
     */
    @Override
    public String toString() {
        return String.format("%1$s|%2$s|%3$s\n-+-+-\n%4$s|%5$s|%6$s\n-+-+-\n%7$s|%8$s|%9$s", board[0][0], board[0][1],
                board[0][2], board[1][0], board[1][1], board[1][2], board[2][0], board[2][1], board[2][2]);
    }
}
