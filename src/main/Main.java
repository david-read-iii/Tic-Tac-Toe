package main;

import java.util.Scanner;

public class Main {

    /**
     * A command line program that simulates a tic-tac-toe game between a player and a computer. The player takes
     * actions according to the coordinates the player provides. The computer takes actions according to the minimax
     * algorithm.
     */
    public static void main(String[] args) {

        Board board = new Board('X', 'O');
        Scanner scanner = new Scanner(System.in);

        // Print the blank state of the board.
        System.out.println(board.toString() + "\n");

        while(true) {

            System.out.println("PLAYER'S TURN (X): ");

            // Determine the player's choice of action.
            System.out.print("Enter row: ");
            int row = scanner.nextInt();

            System.out.print("Enter column: ");
            int column = scanner.nextInt();

            // Take the action the player specifies and print the state of the board after the action is made.
            board.makeMove(new Move(row, column), 1);

            System.out.println("\n" + board.toString() + "\n");

            // If the board is in a terminal state, break out of this loop.
            if (terminalTest(board))
                break;

            System.out.println("CPU'S TURN (O): ");

            // Determine the cpu's choice of action, take it, and print the state of the board after the action is made.
            board.makeMove(minimaxDecision(board), 2);

            System.out.println("\n" + board.toString() + "\n");

            // If the board is in a terminal state, break out of this loop.
            if (terminalTest(board))
                break;
        }

        // Print the terminal result of the game.
        if(utility(board) == 10)
            System.out.println("PLAYER won");
        else if(utility(board) == -10)
            System.out.println("CPU won");
        else
            System.out.println("Draw");
    }

    /**
     * Returns the next optimal move the minimizing player should make.
     */
    public static Move minimaxDecision(Board board) {
        Move result = new Move(-1, -1);
        int v = Integer.MAX_VALUE;

        // Iterate through all possible actions the CPU may take on this turn.
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board.getBoard()[i][j] == 0) {

                    // Take this action on the board.
                    board.makeMove(new Move(i, j), 2);

                    // Get the utility value of this action.
                    int w = minimax(board, true);

                    // Set the resulting action as the action with the maximum utility value of all possible actions the CPU may take.
                    if (w < v) {
                        v = w;
                        result = new Move(i, j);
                    }

                    // Undo this action on the board.
                    board.undoMove(new Move(i, j));

                }
        return result;
    }

    /**
     * Helper method to minimaxDecision().
     * @param isMax True when it is the maximizing player's turn.
     */
    public static int minimax(Board board, Boolean isMax) {

        // Return the utility value if the board is in a terminal state.
        if (terminalTest(board))
            return utility(board);

        if (isMax) {

            int v = Integer.MIN_VALUE;

            // Iterate through all possible actions the player may take on this turn.
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (board.getBoard()[i][j] == 0) {

                        // Take this action.
                        board.makeMove(new Move(i, j), 1);

                        // Set the resulting utility value as the maximum utility value of all possible actions the player may take.
                        v = Integer.max(v, minimax(board, !isMax));

                        // Undo this action.
                        board.undoMove(new Move(i, j));
                    }

            return v;

        } else {

            int v = Integer.MAX_VALUE;

            // Iterate through all possible actions the CPU may take on this turn.
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (board.getBoard()[i][j] == 0) {

                        // Take this action.
                        board.makeMove(new Move(i, j), 2);

                        // Set the resulting utility value as the minimum utility value of all possible actions the CPU may take.
                        v = Integer.min(v, minimax(board, !isMax));

                        // Undo this action.
                        board.undoMove(new Move(i, j));
                    }

            return v;

        }
    }

    /**
     * Returns true when the board is in a terminal state.
     */
    public static Boolean terminalTest(Board board) {

        // Check rows for a victory.
        for (int i = 0; i < 3; i++)
            if (board.getBoard()[i][0] == board.getBoard()[i][1] && board.getBoard()[i][1] == board.getBoard()[i][2] && board.getBoard()[i][0] != 0)
                return true;

        // Check columns for a victory.
        for (int j = 0; j < 3; j++)
            if (board.getBoard()[0][j] == board.getBoard()[1][j] && board.getBoard()[1][j] == board.getBoard()[2][j] && board.getBoard()[0][j] != 0)
                return true;

        // Check diagonals for a victory.
        if (board.getBoard()[0][0] == board.getBoard()[1][1] && board.getBoard()[1][1] == board.getBoard()[2][2] && board.getBoard()[0][0] != 0)
            return true;
        if (board.getBoard()[0][2] == board.getBoard()[1][1] && board.getBoard()[1][1] == board.getBoard()[2][0] && board.getBoard()[0][2] != 0)
            return true;

        // Check if there are empty spaces on the board.
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board.getBoard()[i][j] == 0)
                    return false;

        return true;
    }

    /**
     * Returns the utility value of a board in a terminal state. A positive utility value indicates that the given
     * board favors the maximizing player. A negative utility value indicates that the given board favors the minimizing
     * player. A neutral utility value indicates that the given board favors neither player.
     */
    public static int utility(Board board) {

        // Check rows for a victory.
        for (int i = 0; i < 3; i++)
            if (board.getBoard()[i][0] == board.getBoard()[i][1] && board.getBoard()[i][1] == board.getBoard()[i][2])
                if (board.getBoard()[i][0] == 'X')
                    return 10;
                else if (board.getBoard()[i][0] == 'O')
                    return -10;

        // Check columns for a victory.
        for (int j = 0; j < 3; j++)
            if (board.getBoard()[0][j] == board.getBoard()[1][j] && board.getBoard()[1][j] == board.getBoard()[2][j])
                if (board.getBoard()[0][j] == 'X')
                    return 10;
                else if (board.getBoard()[0][j] == 'O')
                    return -10;

        // Check diagonals for a victory.
        if (board.getBoard()[0][0] == board.getBoard()[1][1] && board.getBoard()[1][1] == board.getBoard()[2][2])
            if (board.getBoard()[0][0] == 'X')
                return 10;
            else if (board.getBoard()[0][0] == 'O')
                return -10;
        if (board.getBoard()[0][2] == board.getBoard()[1][1] && board.getBoard()[1][1] == board.getBoard()[2][0])
            if (board.getBoard()[0][2] == 'X')
                return 10;
            else if (board.getBoard()[0][2] == 'O')
                return -10;

        // Neither player has won.
        return 0;
    }
}