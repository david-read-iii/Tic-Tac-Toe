package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> computerPositions = new ArrayList<Integer>();

    public static void main(String[] args) {
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        printGameBoard(gameBoard);

        while (true) {
            Scanner scan = new Scanner(System.in);

            System.out.println("Enter your placement 1-9");
            int playerPosition = scan.nextInt();

            while (playerPositions.contains(playerPosition) || computerPositions.contains(playerPosition)) {
                System.out.println("Taken, enter a vaid number: ");
                playerPosition = scan.nextInt();
            }
            placePiece(gameBoard, playerPosition, "player");
            String win = checkWinner();
            System.out.println(win);
            if (win.length() > 0) {
                System.out.println(win);
                break;
            }

            Random r = new Random();

            int computerPosition = r.nextInt(9) + 1;

            while (playerPositions.contains(computerPosition) || computerPositions.contains(computerPosition)) {
                System.out.println("Taken, enter a vaid number: ");
                computerPosition = r.nextInt(9) + 1;
            }
            placePiece(gameBoard, computerPosition, "computer");

            printGameBoard(gameBoard);

            win = checkWinner();
            if (win.length() > 0) {
                System.out.println(win);
                break;
            }
        }
    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int position, String user) {
        char symbol = ' ';
        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(position);
        } else if (user.equals("computer")) {
            symbol = 'O';
            computerPositions.add(position);
        }

        switch (position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);
        List line1Row = Arrays.asList(1, 4, 7);
        List line2Row = Arrays.asList(2, 5, 8);
        List line3Row = Arrays.asList(3, 6, 9);
        List cro1Row = Arrays.asList(1, 5, 9);
        List cro2Row = Arrays.asList(7, 5, 3);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(middleRow);
        winning.add(bottomRow);
        winning.add(line1Row);
        winning.add(line2Row);
        winning.add(line3Row);
        winning.add(cro1Row);
        winning.add(cro2Row);

        for (List l : winning) {
            if (playerPositions.containsAll(l)) {
                return "good job, you won";
            } else if (computerPositions.containsAll(l)) {
                return "you lose";
            } else if (playerPositions.size() + computerPositions.size() == 9) {
                return "it's a tie";
            }
        }
        return "";

    }
}