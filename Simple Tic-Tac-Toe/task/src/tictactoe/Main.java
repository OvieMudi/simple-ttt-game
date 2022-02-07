package tictactoe;

import java.util.Scanner;

public class Main {
    private static boolean isGameOver = false;
    private static boolean xIsWinner = false;
    private static boolean oIsWinner = false;
    private static int charSum = 0;
    private static int xCount = 0;
    private static int oCount = 0;
    private static char lastPlayed;
    private static int[][] grid;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        createGrid();
        printPositions();
        while (!isGameOver) {
            setNewPosition();
            computeResult();
        }
    }

    private static void setNewPosition() {
        int row;
        int col;
        System.out.print("Enter the coordinates: ");
        String coordinates = scanner.nextLine();
        String[] arr = coordinates.split("\\s");

        try {
            row = Integer.parseInt(arr[0]);
            col = Integer.parseInt(arr[1]);
        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            setNewPosition();
            return;
        }

        if (row < 1 || col < 1 || row > 3 || col > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            setNewPosition();
            return;
        }

        if (grid[row - 1][col - 1] != 0) {
            System.out.println("This cell is occupied! Choose another one!");
            setNewPosition();
            return;
        }

        char piece = getPiece();
        grid[row - 1][col - 1] = piece;
        printPositions();
    }

    private static char getPiece() {
        char piece = 'X';
        if (lastPlayed == 'X') {
            piece = 'O';
            oCount++;
        } else {
            xCount++;
        }
        lastPlayed = piece;
        return piece;
    }

    private static void computeResult() {
        checkHorizontal();
        checkVertical();
        checkDiagonals();
        checkImpossible();
        displayWinner();
        displayDraw();
    }


    private static void checkImpossible() {
        int diff = Math.abs(xCount - oCount);
        if (diff > 1 || (xIsWinner && oIsWinner)) {
            System.out.println("Impossible");
            isGameOver = true;
        }
    }


    private static void checkHorizontal() {
        for (int[] chars : grid) {
            charSum = chars[0] + chars[1] + chars[2];
            checkResult();
        }
    }

    private static void checkVertical() {
        int column = 0;
        for (int[] ignored : grid) {
            if (isGameOver) {
                break;
            }
            charSum = grid[0][column] + grid[1][column] + grid[2][column];
            checkResult();
            column++;
        }
    }

    private static void checkDiagonals() {
        checkMainDiagonal();
        checkMinorDiagonal();
    }

    private static void checkMainDiagonal() {
        if (isGameOver) {
            return;
        }
        for (int i = 0; i < grid.length; i++) {
            charSum += grid[i][i];
        }
        checkResult();
    }

    private static void checkMinorDiagonal() {
        if (isGameOver) {
            return;
        }
        int lastIndex = grid.length - 1;
        for (int i = 0; i <= lastIndex; i++) {
            charSum += grid[i][lastIndex - i];
        }
        checkResult();
    }

    private static void checkResult() {
        setWinner();
        charSum = 0;
    }

    private static void displayWinner() {
        if (isGameOver) {
            return;
        }
        if (xIsWinner) {
            System.out.println("X wins");
            isGameOver = true;
        } else if (oIsWinner) {
            System.out.println("O wins");
            isGameOver = true;
        }
    }

    private static void setWinner() {
        if (charSum == 264) {
            xIsWinner = true;
        } else if (charSum == 237) {
            oIsWinner = true;
        }
    }

    private static void displayDraw() {
        int minCount = 9;
        boolean notFinished = (xCount + oCount) < minCount && !xIsWinner && !oIsWinner;
        if (!isGameOver && !oIsWinner && !xIsWinner && !notFinished) {
            System.out.println("Draw");
            isGameOver = true;
        }
    }

    private static void createGrid() {
        grid = new int[][]{
                {0, 0, 0,},
                {0, 0, 0,},
                {0, 0, 0,},
        };
    }

    private static void printPositions() {
        System.out.println("---------");
        for (int[] row : grid) {
            System.out.print("| ");
            for (int item : row) {
                if (item == 0) {
                    System.out.print("  ");
                } else {
                    System.out.printf("%c ", item);
                }
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
}
