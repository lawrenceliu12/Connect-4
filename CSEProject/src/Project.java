import java.util.Scanner;

//Lawrence Liu
//113376858

public class Project {
    public static void main(String[] args) {
        String[][] board = createBoard();
        printBoard(board);

        int count = 0;

        while(count <= 42){
            if (count % 2 == 0){ //even means red move
                redMove(board);
            }
            else{ //odd means yellow move
                yellowMove(board);
            }

            count++;

            printBoard(board);

            if (count == 42){
                System.out.println("Tie.");
            }

            if (checkWinner(board).contains("wins.")){
                System.out.println(checkWinner(board));
                break;
                }
            }
        }

    public static String[][] createBoard() {
        String[][] board = new String[7][15];
        for (int i = 0; i < board.length; i++) {
            for (int k = 0; k < board[i].length; k++) {
                if (k % 2 == 0) {
                    board[i][k] = "|";
                }
                else {
                    board[i][k] = " ";
                }
                if (i == 6) {
                    board[i][k] = "-";
                }
            }
        }
        return board;
    }

    public static void printBoard(String[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int k = 0; k < board[i].length; k++) {
                System.out.print(board[i][k]);
            }
            System.out.println();
        }
    }

    public static void redMove(String[][] board) {
        Scanner input = new Scanner(System.in);
        System.out.print("Drop a red disk at column (0 - 6): ");
        int num = input.nextInt();

        while (num >= 7 || num < 0 || board[0][2 * num + 1].equals("R") || board[0][2* num + 1].equals("Y")){
            System.out.print("Out of bounds. Re-enter a value in a different column: ");
            num = input.nextInt();
        }
        int num1 = 2 * num + 1;

            for (int i = 5; i >= 0; i--) {
                if (board[i][num1].equals(" ")) {
                    board[i][num1] = "R";
                    break;
            }
        }
    }

    public static void yellowMove(String[][] board) {
        Scanner input = new Scanner(System.in);
        System.out.print("Drop a yellow disk at column (0 - 6): ");
        int num = input.nextInt();

        while (num >= 7 || num < 0 || board[0][2 * num + 1].equals("Y") || board[0][2 * num + 1].equals("R")){
            System.out.print("Out of bounds. Re-enter a value in a different column: ");
            num = input.nextInt();
        }
        int num1 = 2 * num + 1;

        for (int i = 5; i >= 0; i--) {
            if (board[i][num1].equals(" ")) {
                board[i][num1] = "Y";
                break;
            }
        }
    }

    public static String checkVertical(String[][] board){
        int countR = 0;
        int countY = 0;
        boolean verticalR;
        boolean verticalY;

        for (int i = 0; i < board[0].length; i ++){
            for (int k = 0; k < board.length - 1; k++) {
                verticalR = board[k][i].equals("R") && board[k][i].equals(board[k + 1][i]);
                verticalY = board[k][i].equals("Y") && board[k][i].equals(board[k + 1][i]);

                if (verticalR){
                    countR++;
                }
                else{
                    countR = 1;
                }
                if (countR == 4){
                    return board[k][i];
                }

                if (verticalY){
                    countY++;
                }
                else{
                    countY = 1;
                }
                if (countY == 4){
                    return board[k][i];
                }
            }
        }
        return "";
    }

    public static String checkHorizontal(String[][] board){
        for (int i = 0; i < board.length; i++) {
            for (int k = 1; k < 8; k += 2) {
                boolean horizontalCheck1 = !board[i][k].equals(" ");
                boolean horizontalCheck2 = board[i][k].equals(board[i][k+2]);
                boolean horizontalCheck3 = board[i][k+2].equals(board[i][k+4]);
                boolean horizontalCheck4 = board[i][k+4].equals(board[i][k+6]);
                if (horizontalCheck1 && horizontalCheck2 && horizontalCheck3 && horizontalCheck4){
                    return board[i][k];
                }
            }
        }
        return "";
    }

    public static String checkLeftDiagonal(String[][] board){
        for (int i = 3; i < board.length; i++){
            for (int k = 1; k < 7; k += 2) {
                boolean diagonalCheck1 = !board[i][k].equals(" ");
                boolean diagonalCheck2 = board[i][k].equals(board[i-1][k+2]);
                boolean diagonalCheck3 = board[i-1][k+2].equals(board[i-2][k+4]);
                boolean diagonalCheck4 = board[i-2][k+4].equals(board[i-3][k+6]);
                if (diagonalCheck1 && diagonalCheck2 && diagonalCheck3 && diagonalCheck4){
                    return board[i][k];
                }
            }
        }
        return "";
    }

    public static String checkRightDiagonal(String[][] board) {
        for (int i = 3; i < board.length; i++) {
            for (int k = 13; k > 7; k -= 2) {
                boolean diagonalCheck1 = !board[i][k].equals(" ");
                boolean diagonalCheck2 = board[i][k].equals(board[i - 1][k - 2]);
                boolean diagonalCheck3 = board[i - 1][k - 2].equals(board[i - 2][k - 4]);
                boolean diagonalCheck4 = board[i - 2][k - 4].equals(board[i - 3][k - 6]);
                if (diagonalCheck1 && diagonalCheck2 && diagonalCheck3 && diagonalCheck4) {
                    return board[i][k];
                }
            }
        }
        return "";
    }

    public static String checkWinner (String[][] board) {
        if (checkHorizontal(board).equals("R") || checkVertical(board).equals("R") || checkLeftDiagonal(board).equals("R") || checkRightDiagonal(board).equals("R")) {
            return "Red wins.";
        }
        else if (checkHorizontal(board).equals("Y") || checkVertical(board).equals("Y") || checkLeftDiagonal(board).equals("Y") || checkRightDiagonal(board).equals("Y")) {
            return "Yellow wins.";
        }
        return "";
    }
}